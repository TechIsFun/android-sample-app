package com.github.techisfun.cleanarchitecture.base

import androidx.fragment.app.Fragment
import com.github.techisfun.cleanarchitecture.utils.renderError
import com.github.techisfun.cleanarchitecture.utils.renderLoading

open class BaseFragment: Fragment() {

    protected fun renderLoading(isLoading: Boolean) {
        activity?.renderLoading(isLoading)
    }

    protected fun renderError(error: Exception) {
        activity?.renderError(error)
    }
}
