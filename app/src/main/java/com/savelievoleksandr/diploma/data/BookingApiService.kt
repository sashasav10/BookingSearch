package com.savelievoleksandr.diploma.data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BookingApiService {
    @Headers("X-RapidAPI-Key:b1a7f67b18msh424b6cb334a03c7p1396e6jsna22b094ae39b")
    @GET("v1/hotels/search")
    fun getHotel(
        @Query("checkout_date") checkout_date: String,
        @Query("units") units: String,
        @Query("dest_id") dest_id: Int,
        @Query("dest_type") dest_type: String,
        @Query("locale") locale: String,
        @Query("adults_number") adults_number: Int,
        @Query("order_by") order_by: String,
        @Query("filter_by_currency") filter_by_currency: String,
        @Query("checkin_date") checkin_date: String,
        @Query("room_number") room_number: Int
    ): Call<HotelDto>

}