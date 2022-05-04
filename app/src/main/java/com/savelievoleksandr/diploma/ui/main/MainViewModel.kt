package com.savelievoleksandr.diploma.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savelievoleksandr.diploma.data.HotelDto
import com.savelievoleksandr.diploma.data.HotelRepository
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
            hotelRepository.getHotel() {
                _popularHotelResult.value = it
            }
        }
    }
}