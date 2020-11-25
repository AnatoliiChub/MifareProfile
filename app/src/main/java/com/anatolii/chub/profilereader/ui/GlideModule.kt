package com.anatolii.chub.profilereader.ui

import android.content.Context
import com.anatolii.chub.profilereader.R
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Priority
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey


@GlideModule
class GlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.setDefaultRequestOptions(
            RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(CenterCrop())
                .priority(Priority.LOW)
                .signature(ObjectKey(System.currentTimeMillis().toShort()))
                .placeholder(R.drawable.progress)
                .error(R.drawable.ic_baseline_image_not_supported)
        )
    }
}