package com.sun.funnytoeic.ui.base

import android.content.Context
import android.content.Intent

interface BaseActivityArgs {
    fun intent(context: Context): Intent
    fun launch(context: Context) = context.startActivity(intent(context))
}
