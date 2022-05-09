package com.savelievoleksandr.diploma.ui.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savelievoleksandr.diploma.data.locations.LocationDto
import com.savelievoleksandr.diploma.data.locations.LocationRepository
import kotlinx.coroutines.launch

class FilterViewModel: ViewModel() {
//    private val _locationResult = MutableLiveData<LocationDto>()
//    var locationResult: LiveData<LocationDto> = _locationResult
//
//    private val hotelRepository = LocationRepository()
//
//    fun getLocation(cityName:String) {
//        viewModelScope.launch {
//            hotelRepository.getLocation(cityName) {
//                _locationResult.value = it
//            }
//        }
//        locationResult=_locationResult
//    }
}