package com.sun.funnytoeic.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sun.funnytoeic.BR

abstract class BaseActivity<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel, Args: BaseActivityArgs> :
    AppCompatActivity() {

    private lateinit var viewBinding: ViewBinding
    protected abstract val viewModel: ViewModel
    protected abstract val args: Args

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.lifecycleOwner = this
    }

    protected fun hideActionBar() = supportActionBar?.hide()

    abstract fun initView()

    abstract fun observeViewModel()
}
