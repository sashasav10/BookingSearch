package com.savelievoleksandr.diploma.data.locations

import com.squareup.moshi.Json

class LocationDto : ArrayList<LocationDtoItem>()

data class LocationDtoItem(
    @Json(name = "city_name")  val city_name: String,
    @Json(name = "country")  val country: String,
    @Json(name = "dest_id") val dest_id: String,
    @Json(name = "dest_type") val dest_type: String,
    @Json(name = "hotels") val hotels: Int,
    @Json(name = "image_url") val image_url: String,
    @Json(name = "label") val label: String,
    @Json(name = "name") val name: String,
    @Json(name = "region")  val region: String,
)
