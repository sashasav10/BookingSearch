package com.savelievoleksandr.diploma.data.photo

import com.squareup.moshi.Json

class PhotoDto : ArrayList<PhotoDtoItem>()

data class PhotoDtoItem(
    @Json(name = "photo_id") val photo_id: Int,
    @Json(name = "url_1440") val url_1440: String,
    @Json(name = "url_max") val url_max: String,
)
