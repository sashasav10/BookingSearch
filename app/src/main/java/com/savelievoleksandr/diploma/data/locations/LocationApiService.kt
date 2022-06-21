package com.savelievoleksandr.diploma.data.locations

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LocationApiService {
    @Headers("X-RapidAPI-Key:b1a7f67b18msh424b6cb334a03c7p1396e6jsna22b094ae39b")
    @GET("v1/hotels/locations")
    fun getLocation(
        @Query("locale") locale: String,
        @Query("name") name: String,
    ): Call<LocationDto>

}