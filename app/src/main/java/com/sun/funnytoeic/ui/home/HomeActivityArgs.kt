package com.sun.funnytoeic.ui.home

import android.content.Context
import android.content.Intent
import com.sun.funnytoeic.ui.base.BaseActivityArgs

class HomeActivityArgs private constructor() : BaseActivityArgs {

    override fun intent(context: Context) = Intent(context, HomeActivity::class.java)

    companion object {
        @JvmStatic
        fun instance() = HomeActivityArgs()
    }
}
