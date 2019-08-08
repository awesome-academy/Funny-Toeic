package com.sun.funnytoeic.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sun.funnytoeic.R
import com.sun.funnytoeic.databinding.ActivitySplashBinding
import com.sun.funnytoeic.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashActivityViewModel>() {

    override val viewModel: SplashActivityViewModel by viewModel()
    override val layoutId = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
        viewModel.apply {
            loadingDataProgress.observe(this@SplashActivity, Observer { newProgress ->
                barLoadingData?.run {
                    progress = newProgress
                    if (progress == max) startHomeActivity()
                }
            })
        }
    }

    private fun initView() {
        hideActionBar()
    }

    // Start home activity
    private fun startHomeActivity() {
    }
}
