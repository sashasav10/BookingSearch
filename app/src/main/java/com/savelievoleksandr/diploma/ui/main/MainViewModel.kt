package com.savelievoleksandr.diploma.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savelievoleksandr.diploma.data.hotels.HotelDto
import com.savelievoleksandr.diploma.data.hotels.HotelRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _popularHotelResult = MutableLiveData<HotelDto>()
    val popularHotelResult: LiveData<HotelDto> = _popularHotelResult

    private val hotelRepository = HotelRepository()

    init {
        getPopularHotel()
    }

    private fun getPopularHotel() {
        viewModelScope.launch {
            hotelRepository.getHotel(
                "2022-08-12", 220, "country", 2,
                "UAH", "2022-08-10", 1,null
            ) {
                _popularHotelResult.value = it
            }
        }
    }
}
