package com.ajithvgiri.nasa.data

import com.ajithvgiri.nasa.data.model.Photos

class PhotosTestData {

    companion object{
        private const val copyright = "Rui Liao"
        private const val date = "2019-12-31"
        private const val explanation = "The small, northern constellation Triangulum harbors this magnificent face-on spiral galaxy, M33. Its popular names include the Pinwheel Galaxy or just the Triangulum Galaxy. M33 is over 50,000 light-years in diameter, third largest in the Local Group of galaxies after the Andromeda Galaxy (M31), and our own Milky Way. About 3 million light-years from the Milky Way, M33 is itself thought to be a satellite of the Andromeda Galaxy and astronomers in these two galaxies would likely have spectacular views of each other's grand spiral star systems. As for the view from planet Earth, this sharp image shows off M33's blue star clusters and pinkish star forming regions along the galaxy's loosely wound spiral arms. In fact, the cavernous NGC 604 is the brightest star forming region, seen here at about the 7 o'clock position from the galaxy center. Like M31, M33's population of well-measured variable stars have helped make this nearby spiral a cosmic yardstick for establishing the distance scale of the Universe."
        private const val hdurl = "https://apod.nasa.gov/apod/image/1912/M33-HaLRGB-RayLiao.jpg"
        private const val media_type = "image"
        private const val service_version = "v1"
        private const val title = "M33: The Triangulum Galaxy"
        private const val url = "https://apod.nasa.gov/apod/image/1912/M33-HaLRGB-RayLiao1024.jpg"

        val photos = Photos(copyright,date, explanation, hdurl, media_type, service_version, title, url)
    }
    val listOfPhotos: List<Any>
        get() {
            val pictureDetailsList: MutableList<Photos> =
                ArrayList()
            for (i in 0..9) {
                pictureDetailsList.add(photos)
            }
            return pictureDetailsList
        }
}