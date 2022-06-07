package com.savelievoleksandr.diploma.data.locations

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LocationApiService {
    @Headers("X-RapidAPI-Key:e2272108d0msh3ad38c723a2566ep1c166cjsnfdb2855d6c04")
    @GET("v1/hotels/locations")
    fun getLocation(
        @Query("locale") locale: String,
        @Query("name") name: String,
    ): Call<LocationDto>

}