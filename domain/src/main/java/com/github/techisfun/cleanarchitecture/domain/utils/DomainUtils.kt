package com.github.techisfun.cleanarchitecture.domain.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import java.util.regex.Pattern

/**
 * @author Andrea Maglie
 */


fun <A, B> zipLiveData(a: LiveData<A>, b: LiveData<B>): LiveData<Pair<A, B>> {
    return MediatorLiveData<Pair<A, B>>().apply {
        var lastA: A? = null
        var lastB: B? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            if (localLastA != null && localLastB != null)
                this.value = Pair(localLastA, localLastB)
        }

        addSource(a) {
            lastA = it
            update()
        }
        addSource(b) {
            lastB = it
            update()
        }
    }
}

fun String.trimToNull(): String? {
    return if (this.isBlank()) null else this
}

fun String?.trimToEmpty(): String {
    return this?.trim() ?: ""
}

// taken from https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/util/Patterns.java
private val EMAIL_ADDRESS = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun validateEmail(email: String): Boolean {
    return email.isNotBlank() && EMAIL_ADDRESS.matcher(email).matches()
}