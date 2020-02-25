package com.ajithvgiri.nasa.ui.home

import android.view.View
import com.ajithvgiri.nasa.data.model.Photos

interface OnItemClickListener {
    fun getItemImage(view: View,photos:Photos, position: Int)
}