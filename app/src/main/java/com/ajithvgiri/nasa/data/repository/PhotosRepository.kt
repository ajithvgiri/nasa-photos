package com.ajithvgiri.nasa.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ajithvgiri.nasa.data.model.Photos
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.IOException
import java.lang.reflect.Type

class PhotosRepository {

    val listOfPhotosMutableLiveData = MutableLiveData<List<Photos>>()
    private val moshi = Moshi.Builder().build()
    private val type: Type = Types.newParameterizedType(List::class.java, Photos::class.java)
    private val jsonAdapter: JsonAdapter<List<Photos>> = moshi.adapter(type)

    fun getPhotos(context: Context): LiveData<List<Photos>> {
        getAssetJsonData(context)?.let { json ->
            val photos: List<Photos>? = jsonAdapter.fromJson(json)
            photos?.forEach { photo ->
                Log.d("Photos", "response from photos ${photo.title}")
            }
            photos?.let { listOfPhotos ->
                listOfPhotosMutableLiveData.value = listOfPhotos
            }
        }
        return listOfPhotosMutableLiveData
    }

    private fun getAssetJsonData(context: Context): String? {
        val json: String
        try {
            val inputStream = context.assets.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.use { it.read(buffer) }
            json = String(buffer)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        // print the data
        Log.i("data", json)
        return json
    }
}