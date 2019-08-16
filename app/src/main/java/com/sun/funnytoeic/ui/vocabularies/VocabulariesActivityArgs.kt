package com.sun.funnytoeic.ui.vocabularies

import android.content.Context
import android.content.Intent
import com.sun.funnytoeic.ui.base.BaseActivityArgs

class VocabulariesActivityArgs private constructor() : BaseActivityArgs {

    override fun intent(context: Context) = Intent(context, VocabulariesActivity::class.java)

    companion object {
        @JvmStatic
        fun instance() = VocabulariesActivityArgs()
    }
}
