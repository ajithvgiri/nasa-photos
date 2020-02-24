package com.ajithvgiri.nasa.data

import java.io.Serializable

data class PhotoDetails(
    var copyright: String,
    var date: String,
    var explanation: String,
    var hdurl: String,
    var media_type: String,
    var service_version: String,
    var title: String,
    var url: String
) : Serializable
