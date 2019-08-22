package com.sun.funnytoeic.ui.home

import android.view.View
import com.sun.funnytoeic.R
import com.sun.funnytoeic.databinding.ActivityHomeBinding
import com.sun.funnytoeic.ui.base.BaseActivity
import com.sun.funnytoeic.ui.play.PlayActivityArgs
import com.sun.funnytoeic.ui.store.StoreActivityArgs
import com.sun.funnytoeic.ui.vocabularies.VocabulariesActivityArgs
import com.sun.funnytoeic.utils.assignViews
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>(),
    View.OnClickListener {

    override val viewModel: HomeActivityViewModel by viewModel()
    override val layoutId = R.layout.activity_home

    override fun initView() {
        hideActionBar()
        assignViews(
            imageVocabulariesIcon, textVocabulariesLabel,
            imagePlayButton, textPlayLabel,
            imageStoreIcon, textStoreLabel
        )
    }

    override fun onClick(view: View) {
        when (view) {
            imageVocabulariesIcon,
            textVocabulariesLabel -> VocabulariesActivityArgs.instance().launch(this)
            imagePlayButton,
            textPlayLabel -> PlayActivityArgs.instance().launch(this)
            imageStoreIcon,
            textStoreLabel -> StoreActivityArgs.instance().launch(this)
        }
    }

    override fun observeViewModel() = viewModel.run {
    }
}
