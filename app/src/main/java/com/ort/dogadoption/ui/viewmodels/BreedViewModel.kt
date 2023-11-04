package com.ort.dogadoption.ui.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.dogadoption.data.api.DogApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

class BreedViewModel : ViewModel() {

    private val _breeds = MutableLiveData<Map<String, List<String>>>()
    val breeds: LiveData<Map<String, List<String>>> = _breeds
    fun setBreeds (breeds: Map<String, List<String>>) {
        _breeds.postValue(breeds)
    }
}