package com.savelievoleksandr.diploma.data.locations

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface LocationApiService {
    @Headers("X-RapidAPI-Key:27825dae23msh675104a9c78f89ep13e682jsn3edba06cd5c4")
    @GET("v1/hotels/locations")
    fun getLocation(
        @Query("locale") locale: String,
        @Query("name") name: String,
    ): Call<LocationDto>

}