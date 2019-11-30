package com.github.techisfun.cleanarchitecture.tasklist

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.github.techisfun.cleanarchitecture.R
import com.github.techisfun.cleanarchitecture.base.BaseFragment
import com.github.techisfun.cleanarchitecture.base.DataBindingRecyclerViewAdapter
import dagger.android.support.AndroidSupportInjection
import java.lang.IllegalArgumentException
import javax.inject.Inject

class TaskListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: TaskListViewModelFactory

    private lateinit var viewModel: TaskListViewModel

    private lateinit var binding: ViewDataBinding

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.task_list_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        recyclerView = binding.root.findViewById(R.id.recyclerView)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskListViewModel::class.java)

        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            renderLoading(it == true)
        })

        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { renderError(it) }
        })

        viewModel.uiModelLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { items -> renderUiModel(items) }
        })

    }

    private fun renderUiModel(list: List<TaskUiModel>) {
        recyclerView?.adapter = ExampleListAdapter(list, viewModel)
    }

    private inner class ExampleListAdapter(items: List<Any>, viewModelInstance: ViewModel) :
        DataBindingRecyclerViewAdapter(items, viewModelInstance, viewLifecycleOwner) {

        override fun getLayoutIdForPosition(position: Int): Int {
            val item = getObjForPosition(position)
            return when (item) {
                is TaskUiModel -> R.layout.task_item_layout
                else -> throw IllegalArgumentException("unknown model: $item")
            }
        }

        override fun onItemClicked(position: Int) {
            val item = getObjForPosition(position)
            when (item) {
                is TaskUiModel -> {
                    TODO("do something with item, example: viewModel.showDetails(item, viewLifecycleOwner)")
                }
            }
        }
    }

}
