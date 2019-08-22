package com.sun.funnytoeic.ui.store

import android.content.Context
import android.content.Intent
import com.sun.funnytoeic.ui.base.BaseActivityArgs

class StoreActivityArgs private constructor() : BaseActivityArgs {

    override fun intent(context: Context) = Intent(context, StoreActivity::class.java)

    companion object {
        fun instance() = StoreActivityArgs()
    }
}
