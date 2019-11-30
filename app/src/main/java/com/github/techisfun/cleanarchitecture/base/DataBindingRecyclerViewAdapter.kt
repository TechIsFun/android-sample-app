package com.github.techisfun.cleanarchitecture.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.github.techisfun.cleanarchitecture.BR

abstract class DataBindingRecyclerViewAdapter protected constructor(
    var items: List<Any>,
    val viewModel: ViewModel?,
    val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<DataBindingRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        binding.lifecycleOwner = lifecycleOwner
        return MyViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = getObjForPosition(position)
        holder.bind(obj, viewModel)
        holder.itemView.setOnClickListener {
            onItemClicked(position)
        }
    }

    open fun onItemClicked(position: Int) {

    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    protected fun getObjForPosition(position: Int): Any? {
        return items[position]
    }

    fun updateItems(newItems: List<Any>)
    {
        items = newItems
        notifyDataSetChanged()
    }

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    class MyViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Any?, viewModel: ViewModel?) {
            binding.setVariable(BR.model, item)
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }
    }

}
