package com.ajithvgiri.nasa.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


fun ImageView.loadImage(url: String?, thumbUrl: String? = "") {
    val circularProgressDrawable = CircularProgressDrawable(this.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.setColorSchemeColors(Color.WHITE)
    circularProgressDrawable.start()

    GlideApp.with(this).load(url)
        .transition(DrawableTransitionOptions.withCrossFade(NasaGlideModule.drawableCrossFadeFactory))
        .placeholder(circularProgressDrawable)
        .thumbnail(Glide.with(context).load(thumbUrl))
        .error(ColorDrawable(Color.RED))
        .dontAnimate()
        .override(Target.SIZE_ORIGINAL)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                //loadImage(url)
                Log.e(
                    "ImageGridViewHolder",
                    "onLoadFailed image is $url with exception ${e?.message}"
                )
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                //do something when picture already loaded
                return false
            }
        }).into(this)
}