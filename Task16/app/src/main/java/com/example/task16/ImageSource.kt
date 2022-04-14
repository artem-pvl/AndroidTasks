package com.example.task16

data class ImageSource(
    var urlLowRes: String = "",
    var urlHiRes: String = "",
    var description: String = ""
) {
    fun getFromFlickrPhoto(photo: FlickrImageList.Photos.Photo) {
        urlLowRes =
            "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
        urlHiRes =
            "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_z.jpg"
        description = photo.title
    }
}
