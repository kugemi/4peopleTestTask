package com.kugemi.exchangeratestracker.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kugemi.exchangeratestracker.infrastructure.repositories.interfaces.IExchangeRatesRepository
import com.kugemi.exchangeratestracker.model.server_dto.RateItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeRatesViewModel @Inject constructor(private val repository: IExchangeRatesRepository)
    : ViewModel() {

    private val myRates = MutableLiveData<List<RateItem>>()

    val rates: LiveData<List<RateItem>>
        get() = myRates

    init {
        viewModelScope.launch(Dispatchers.IO) {
            myRates.postValue(repository.getRates().rates)
        }
    }
}