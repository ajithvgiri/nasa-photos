package com.ajithvgiri.nasa.utils

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory.Builder
import com.bumptech.glide.signature.ObjectKey


@GlideModule
class NasaGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val memoryCacheSizeBytes = 1024 * 1024 * 50 // 50mb
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, memoryCacheSizeBytes.toLong()))
        builder.setDefaultRequestOptions(requestOptions(context))
    }

    companion object {
        var drawableCrossFadeFactory = Builder().setCrossFadeEnabled(true).build()

        private fun requestOptions(context: Context): RequestOptions {
            return RequestOptions()
                .signature(ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000)))
                .override(200, 200)
                .centerCrop()
                .encodeFormat(Bitmap.CompressFormat.JPEG)
                .encodeQuality(100)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .format(DecodeFormat.PREFER_ARGB_8888)
                .skipMemoryCache(false)
        }
    }
}
