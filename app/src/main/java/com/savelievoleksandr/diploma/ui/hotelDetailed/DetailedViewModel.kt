package com.savelievoleksandr.diploma.ui.hotelDetailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savelievoleksandr.diploma.data.photo.PhotoDto
import com.savelievoleksandr.diploma.data.photo.PhotoRepository
import kotlinx.coroutines.launch

class DetailedViewModel : ViewModel() {
    private val _photoResult = MutableLiveData<PhotoDto>()
    var photoResult: LiveData<PhotoDto> = _photoResult
    private val photoRepository = PhotoRepository()
    fun getPhoto(hotel_id: Int) {
        viewModelScope.launch {
            photoRepository.getPhoto(hotel_id) {
                _photoResult.value = it
            }
            photoResult = _photoResult
        }
    }
}