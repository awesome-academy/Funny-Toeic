package com.sun.funnytoeic.ui.store

import com.sun.funnytoeic.R
import com.sun.funnytoeic.databinding.ActivityStoreBinding
import com.sun.funnytoeic.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreActivity : BaseActivity<ActivityStoreBinding, StoreActivityViewModel>() {

    override val viewModel: StoreActivityViewModel by viewModel()
    override val layoutId = R.layout.activity_store

    override fun initView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun observeViewModel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
