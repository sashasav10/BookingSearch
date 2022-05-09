package com.savelievoleksandr.diploma.data.photo

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PhotoRepository {
    fun getPhoto(
        hotel_id: Int,
        result: (PhotoDto) -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://booking-com.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val photoApiService = retrofit.create(PhotoApiService::class.java)
        photoApiService.getPhoto("en-gb", hotel_id)
            .enqueue(object : Callback<PhotoDto> {
                override fun onResponse(
                    call: Call<PhotoDto>,
                    response: Response<PhotoDto>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { result(it) }
                    } else {
                        Log.d("ErrorSASHA", response.code().toString())
                    }
                }

                override fun onFailure(call: Call<PhotoDto>, t: Throwable) {
                    Log.d("Error", "Error booking.com")
                    t.printStackTrace()
                }
            })
    }
}