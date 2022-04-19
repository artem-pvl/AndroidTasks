package com.example.task16

data class ImageSource(
    val urlLowRes: String,
    val urlHiRes: String,
    val description: String
)


fun getFromFlickrPhoto(photo: FlickrImageList.Photos.Photo): ImageSource {
    val urlLowRes =
        "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
    val urlHiRes =
        "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_z.jpg"
    val description = photo.title
    return ImageSource(urlHiRes = urlHiRes, urlLowRes = urlLowRes, description = description)
}

