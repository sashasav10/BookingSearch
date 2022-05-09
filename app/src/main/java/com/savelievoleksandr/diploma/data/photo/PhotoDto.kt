package com.savelievoleksandr.diploma.data.photo

import com.squareup.moshi.Json

class PhotoDto : ArrayList<PhotoDtoItem>()

data class PhotoDtoItem(
    @Json(name = "photo_id") val photoId: Int,
    @Json(name = "url_1440") val url1440: String,
    @Json(name = "url_max") val urlMax: String,
)
