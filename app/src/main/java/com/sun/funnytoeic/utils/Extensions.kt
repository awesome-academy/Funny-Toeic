package com.sun.funnytoeic.utils

import android.content.Context
import android.widget.Toast

object Extensions {
    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
