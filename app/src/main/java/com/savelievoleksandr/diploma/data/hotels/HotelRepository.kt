package com.savelievoleksandr.diploma.data.hotels

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HotelRepository {
    fun getHotel(
        checkout_date: String,
        dest_id: Int,
        dest_type: String,
        adults_number: Int,
        filter_by_currency: String,
        checkin_date: String,
        room_number: Int,
        children_number: Int?,
        result: (HotelDto) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://booking-com.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val bookingApiService = retrofit.create(BookingApiService::class.java)
        bookingApiService.getHotel(
            checkout_date, "metric", dest_id,
            dest_type, "en-gb", adults_number, "popularity",
            filter_by_currency, checkin_date, room_number, children_number
        )
            .enqueue(object : Callback<HotelDto> {
                override fun onResponse(
                    call: Call<HotelDto>,
                    response: Response<HotelDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { result(it) }
                    } else {
                        Log.d("ErrorSASHA", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<HotelDto>, t: Throwable) {
                    Log.d("ErrorSASHA", "Error booking.com")
                    t.printStackTrace()
                }
            })
    }
}