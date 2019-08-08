package com.sun.funnytoeic.ui.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.sun.funnytoeic.R
import com.sun.funnytoeic.databinding.ActivityHomeBinding
import com.sun.funnytoeic.ui.base.BaseActivity
import com.sun.funnytoeic.ui.splash.SplashActivityViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    override val viewModel: HomeActivityViewModel by viewModel()
    override val layoutId = R.layout.activity_home

    override fun initView() {
        hideActionBar()
    }

    override fun observeViewModel() = viewModel.run {
    }

    companion object {
        fun getProfileIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }
}
