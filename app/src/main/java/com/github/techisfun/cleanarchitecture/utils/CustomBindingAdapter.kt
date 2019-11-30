package com.github.techisfun.cleanarchitecture.utils

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion

/**
 * @author Andrea Maglie
 */

@BindingConversion
fun convertBooleanToVisibility(visibile: Boolean): Int {
    return if (visibile) View.VISIBLE else View.GONE
}

@BindingAdapter("error")
fun bindError(view: EditText, error: String?) {
    view.error = error
}

@BindingAdapter("checked")
fun setChecked(textView: TextView, isChecked: Boolean) {
    textView.isSelected = isChecked
}

@BindingAdapter("src")
fun setImageViewResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}