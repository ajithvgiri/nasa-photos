package com.ajithvgiri.nasa.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ajithvgiri.nasa.data.model.Photos
import com.ajithvgiri.nasa.data.repository.PhotosRepository

class PhotosViewModel(application: Application) : AndroidViewModel(application) {

    private val photosRepository = PhotosRepository()

    val listOfPhotos: LiveData<List<Photos>> = photosRepository.listOfPhotosMutableLiveData

    init {
        val response = photosRepository.getPhotos(application.applicationContext)
    }
}