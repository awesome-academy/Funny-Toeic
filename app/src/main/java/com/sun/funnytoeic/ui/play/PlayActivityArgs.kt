package com.sun.funnytoeic.ui.play

import android.content.Context
import android.content.Intent
import com.sun.funnytoeic.ui.base.BaseActivityArgs

class PlayActivityArgs private constructor(): BaseActivityArgs {

    override fun intent(context: Context) = Intent(context, PlayActivity::class.java)

    companion object {
        @JvmStatic
        fun instance() = PlayActivityArgs()
    }
}
