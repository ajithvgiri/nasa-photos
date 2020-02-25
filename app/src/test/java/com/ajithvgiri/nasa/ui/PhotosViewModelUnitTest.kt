package com.ajithvgiri.nasa.ui

import com.ajithvgiri.nasa.data.PhotosTestData
import com.ajithvgiri.nasa.data.model.Photos
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.lang.reflect.Type


class PhotosViewModelUnitTest {
    private lateinit var photos: Photos
    private val moshi = Moshi.Builder().build()
    private val type: Type = Types.newParameterizedType(List::class.java, Photos::class.java)
    private val jsonAdapter: JsonAdapter<List<Photos>> = moshi.adapter(type)

    @Before
    fun setUpDetailViewModelTest() {
        photos = PhotosTestData.photos
    }

    @Test
    fun getPhotos() {
        try {
            val inputStream = javaClass.classLoader?.getResourceAsStream("assets/data.json")
            val size = inputStream?.available()
            val buffer = size?.let { ByteArray(it) }
            inputStream.use { it?.read(buffer) }
            val json = buffer?.let { String(it) }
            inputStream?.close()
            json?.let {
                val photosList: List<Photos>? = jsonAdapter.fromJson(json)?.reversed()
                photosList?.let { listOfPhotos ->
                    assertEquals(listOfPhotos[0].copyright, photos.copyright)
                    assertEquals(listOfPhotos[0].date, photos.date)
                    assertEquals(listOfPhotos[0].explanation, photos.explanation)
                    assertEquals(listOfPhotos[0].hdurl, photos.hdurl)
                    assertEquals(listOfPhotos[0].media_type, photos.media_type)
                    assertEquals(listOfPhotos[0].service_version, photos.service_version)
                    assertEquals(listOfPhotos[0].title, photos.title)
                    assertEquals(listOfPhotos[0].url, photos.url)
                }
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }


    }
}