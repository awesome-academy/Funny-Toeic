package com.sun.funnytoeic.ui.play

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sun.funnytoeic.R
import com.sun.funnytoeic.data.local.entity.HintImage
import com.sun.funnytoeic.ui.base.UpdatableAdapter
import com.sun.funnytoeic.utils.Constants
import com.sun.funnytoeic.utils.setImageUrl
import kotlinx.android.synthetic.main.item_hint_image.view.*

class HintImagesAdapter(
    hintImages: MutableList<HintImage>
) : UpdatableAdapter<HintImage, HintImagesAdapter.ViewHolder>(hintImages) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_hint_image, parent, false)
    )

    class ViewHolder(view: View) : UpdatableAdapter.ViewHolder<HintImage>(view) {

        private val hintImage by lazyOf(view.imageHint)

        override fun bindData(item: HintImage) {
            hintImage?.setImageUrl(item.url, Constants.HINT_IMAGE_CORNER_RADIUS)
        }
    }
}
