package com.sun.funnytoeic.ui.splash

import android.content.Context
import android.content.Intent
import com.sun.funnytoeic.ui.base.BaseActivityArgs

class SplashActivityArgs private constructor(): BaseActivityArgs {

    override fun intent(context: Context) = Intent(context, SplashActivity::class.java)
}
