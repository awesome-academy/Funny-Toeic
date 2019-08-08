package com.sun.funnytoeic.ui.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.sun.funnytoeic.R
import com.sun.funnytoeic.databinding.ActivitySplashBinding
import com.sun.funnytoeic.ui.base.BaseActivity
import com.sun.funnytoeic.utils.Constants.VALUE_100
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashActivityViewModel>() {

    override val viewModel: SplashActivityViewModel by viewModel()
    override val layoutId = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
        observeViewModel()
    }

    override fun observeViewModel() = viewModel.run {
        done.observe(this@SplashActivity, Observer { done ->
            if (done) {
                barLoadingData.run { progress = max }
                startHomeActivity()
            }
        })
    }

    override fun initView() {
        hideActionBar()
        barLoadingData?.max = VALUE_100
    }

    // Start home activity
    private fun startHomeActivity() {
    }
}
