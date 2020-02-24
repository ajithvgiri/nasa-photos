package com.ajithvgiri.nasa.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ajithvgiri.nasa.data.repository.PhotosRepository

class PhotosViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    init {
        val response = PhotosRepository().getPhotos(application.applicationContext)
    }
}