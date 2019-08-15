package com.sun.funnytoeic.ui.home

import com.sun.funnytoeic.R
import com.sun.funnytoeic.databinding.ActivityHomeBinding
import com.sun.funnytoeic.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel, HomeActivityArgs>() {

    override val viewModel: HomeActivityViewModel by viewModel()
    override val layoutId = R.layout.activity_home
    override val args by lazyOf(HomeActivityArgs())

    override fun initView() {
        hideActionBar()
    }

    override fun observeViewModel() = viewModel.run {
    }
}
