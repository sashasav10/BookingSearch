package com.savelievoleksandr.diploma.data.locations

import android.util.Log
import com.savelievoleksandr.diploma.data.BookingApiService
import com.savelievoleksandr.diploma.data.HotelDto
import com.savelievoleksandr.diploma.data.locations.LocationDtoItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationRepository {
    fun getLocation(locationName:String, result: (LocationDto) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://booking-com.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val locationsApiService = retrofit.create(LocationApiService::class.java)
        locationsApiService.getLocation("en-gb",locationName)
            .enqueue(object : Callback<LocationDto> {
                override fun onResponse(
                    call: Call<LocationDto>,
                    response: Response<LocationDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { result(it) }
                    } else {
                        Log.d("Error", response.code().toString())
                    }
                }
                override fun onFailure(call: Call<LocationDto>, t: Throwable) {
                    Log.d("Error", "Error ")
                    t.printStackTrace()
                }
            })
    }
}