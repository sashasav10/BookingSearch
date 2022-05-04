package com.savelievoleksandr.diploma.data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HotelRepository {
    fun getHotel(result: (HotelDto) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://booking-com.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val bookingApiService = retrofit.create(BookingApiService::class.java)
        bookingApiService.getHotel("2022-07-02","metric",220,
            "country","en-gb",2,"popularity",
            "UAH","2022-07-01",1)
            .enqueue(object : Callback<HotelDto> {
                override fun onResponse(
                    call: Call<HotelDto>,
                    response: Response<HotelDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { result(it) }
                    } else {
                        Log.d("Error", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<HotelDto>, t: Throwable) {
                    Log.d("Error", "Error booking.com")
                    t.printStackTrace()
                }
            })
    }
}