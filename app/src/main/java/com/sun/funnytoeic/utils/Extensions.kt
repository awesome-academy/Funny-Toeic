package com.sun.funnytoeic.utils

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun <T> MutableList<T>.replaceAll(data: List<T>) {
    clear()
    addAll(data)
}
