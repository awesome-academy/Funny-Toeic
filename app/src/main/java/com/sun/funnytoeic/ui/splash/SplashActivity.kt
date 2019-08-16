package com.sun.funnytoeic.ui.splash

import androidx.lifecycle.Observer
import com.sun.funnytoeic.R
import com.sun.funnytoeic.databinding.ActivitySplashBinding
import com.sun.funnytoeic.ui.base.BaseActivity
import com.sun.funnytoeic.ui.home.HomeActivity
import com.sun.funnytoeic.ui.home.HomeActivityArgs
import com.sun.funnytoeic.utils.Constants.MAX_PERCENT
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashActivityViewModel>() {

    override val viewModel: SplashActivityViewModel by viewModel()
    override val layoutId = R.layout.activity_splash

    override fun observeViewModel() = viewModel.run {
        done.observe(this@SplashActivity, Observer { done ->
            if (done) {
                barLoadingData?.run { progress = max }
                HomeActivityArgs.instance().launch(this@SplashActivity)
            }
        })
    }

    override fun initView() {
        hideActionBar()
        barLoadingData?.max = MAX_PERCENT
    }
}
