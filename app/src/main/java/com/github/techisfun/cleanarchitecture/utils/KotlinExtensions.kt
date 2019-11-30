package com.github.techisfun.cleanarchitecture.utils

import android.app.Activity
import android.content.Context
import android.text.InputType
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.techisfun.cleanarchitecture.LoadingFragment
import com.github.techisfun.cleanarchitecture.R
import com.github.techisfun.cleanarchitecture.domain.exceptions.ApiException
import com.github.techisfun.cleanarchitecture.domain.exceptions.GenericNetworkException
import timber.log.Timber

/**
 * @author Andrea Maglie
 */
private const val LOADING_FRAGMENT_TAG = "Loader"

fun FragmentActivity.renderLoading(isLoading: Boolean) {
    var loadingFragment: DialogFragment? = this.supportFragmentManager.findFragmentByTag(LOADING_FRAGMENT_TAG) as DialogFragment?

    if (isLoading) {
        if (loadingFragment == null) {
            loadingFragment = LoadingFragment()
            loadingFragment.show(this.supportFragmentManager,
                LOADING_FRAGMENT_TAG
            )
            try {
                supportFragmentManager.executePendingTransactions()
            } catch (e: Exception) {
                Timber.w(e)
            }
        }
    } else {
        loadingFragment?.dismissAllowingStateLoss()
    }
}


fun FragmentActivity.renderError(exception: Exception) {
    val errorMessage = when(exception) {
        is ApiException -> {
            val message = exception.message
            message ?: getString(R.string.message_generic_web_service_error)
        }
        //is LoginInvalidUserException -> getString(R.string.login_invalid_user)
        //is PasswordMismatchException -> getString(R.string.signup_pws_not_match_confirm_pws_message)
        //is PasswordFormatException -> getString(R.string.signup_pws_invalid_format_message)
        //is SignupException -> getString(R.string.signup_server_error)
        is GenericNetworkException -> getString(R.string.message_generic_web_service_error)
        else -> this.getString(R.string.message_generic_web_service_error)
    }

    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
}


fun Fragment.closeSoftKeyboard() {
    this.view?.let { view ->
        try {
            val inputMethodManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (ignored: RuntimeException) {
        }
    }
}

fun Activity.renderConfirmationDialog(message: String, title: String?, action: () -> Unit) {
    val dialogBuilder = AlertDialog.Builder(this)

    // set message of alert dialog
    dialogBuilder.setMessage(message)
        .setCancelable(false)
        .setPositiveButton(getString(R.string.ok)) { _, _ -> action.invoke() }
    val alert = dialogBuilder.create()
    title?.let { alert.setTitle(title) }
    alert.show()
}

fun Activity.renderTextInputDialog(message: String, title: String?, action: (String) -> Unit) {
    val dialogBuilder = AlertDialog.Builder(this)

    val inputEditText = EditText(this)
    inputEditText.inputType = InputType.TYPE_CLASS_TEXT

    // set message of alert dialog
    dialogBuilder.setMessage(message)
        .setCancelable(false)
        .setView(inputEditText)
        .setPositiveButton(getString(R.string.ok)) { _, _ -> action.invoke(inputEditText.text.toString()) }
        .setNegativeButton(getString(R.string.cancel)) { d, _ -> d.dismiss() }
    val alert = dialogBuilder.create()
    title?.let { alert.setTitle(title) }
    alert.show()
}
