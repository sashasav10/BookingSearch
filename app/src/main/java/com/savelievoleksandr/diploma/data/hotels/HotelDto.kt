package com.savelievoleksandr.diploma.data.hotels
import com.squareup.moshi.Json

data class HotelDto(
    @Json(name = "count") val count: Int,
    @Json(name = "result") val result: List<ResultDto>,
)
data class ResultDto(
    @Json(name = "accommodation_type_name") val accommodation_type_name: String,
    @Json(name = "address") val address: String,
    @Json(name = "city_name_en") val city_name_en: String,
    @Json(name = "country_trans") val country_trans: String,
    @Json(name = "currencycode") val currencycode: String,
    @Json(name = "hotel_id") val hotel_id: Int,
    @Json(name = "hotel_name") val hotel_name: String,
    @Json(name = "max_photo_url") val max_photo_url: String,
    @Json(name = "min_total_price") val min_total_price: Double,
    @Json(name = "review_score") val review_score: Double,
    @Json(name = "distance_to_cc") val distance_to_cc: Double,
    @Json(name = "is_free_cancellable") val is_free_cancellable: Byte,
    @Json(name = "hotel_include_breakfast") val hotel_include_breakfast: Byte,
    @Json(name = "review_score_word") val review_score_word: String,
    @Json(name = "unit_configuration_label") val unit_configuration_label: String,
    @Json(name = "url") val url: String,
)
