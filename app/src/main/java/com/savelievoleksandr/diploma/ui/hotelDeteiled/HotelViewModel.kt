package com.savelievoleksandr.diploma.ui.hotelDeteiled

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savelievoleksandr.diploma.data.HotelDto
import com.savelievoleksandr.diploma.data.hotels.HotelRepository
import com.savelievoleksandr.diploma.data.photo.PhotoDto
import com.savelievoleksandr.diploma.data.photo.PhotoRepository
import kotlinx.coroutines.launch

class HotelViewModel : ViewModel() {
    private val _hotelResult = MutableLiveData<HotelDto>()
    var hotelResult: LiveData<HotelDto> = _hotelResult
    private val hotelRepository = HotelRepository()

    private val _photoResult = MutableLiveData<PhotoDto>()
    var photoResult: LiveData<PhotoDto> = _photoResult
    private val photoRepository = PhotoRepository()
    fun getHotel(
        checkout_date: String,
        dest_id: Int,
        dest_type: String,
        adults_number: Int,
        checkin_date: String,
        room_number: Int,
        children_number: Int,
    ) {
        if (children_number <= 0) {
            viewModelScope.launch {
                hotelRepository.getHotel(
                    checkout_date, dest_id, dest_type, adults_number,
                    "UAH", checkin_date, room_number, null
                ) {
                    _hotelResult.value = it
                }
                hotelResult = _hotelResult
            }
        } else {
            viewModelScope.launch {
                hotelRepository.getHotel(
                    checkout_date, dest_id, dest_type, adults_number,
                    "UAH", checkin_date, room_number, children_number
                ) {
                    _hotelResult.value = it
                }
                hotelResult = _hotelResult
            }
        }
    }

    fun getPhoto(hotel_id: Int) {
        viewModelScope.launch {
            photoRepository.getPhoto(hotel_id) {
                _photoResult.value = it
            }
            photoResult = _photoResult
        }
    }
}