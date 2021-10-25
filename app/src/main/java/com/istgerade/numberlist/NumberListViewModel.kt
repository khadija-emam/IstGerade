package com.istgerade.numberlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istgerade.R
import com.istgerade.data.entity.NumberResponse
import com.istgerade.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.NumberFormatException
import javax.inject.Inject

@HiltViewModel
class NumberListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _error = MutableLiveData<Int>()
    val error: LiveData<Int>
        get() = _error

    private val _navigate = MutableLiveData<String?>()
    val navigate: LiveData<String?>
        get() = _navigate

    private val _newNumber = MutableLiveData<NumberResponse?>()

    val number: LiveData<NumberResponse?>
        get() = _newNumber

    private val _removedNumber = MutableLiveData<NumberResponse?>()

    val removedNumber: LiveData<NumberResponse?>
        get() = _removedNumber
    var userNumber = ""

    fun isEven() {
        val longNumber = stringToLong()
        longNumber?.let {
            viewModelScope.launch {
                try {
                    _progress.value = true
                    val numberResponse = repository.isEven(it)
                    _progress.value = false
                    numberResponse?.num = it
                    _newNumber.value = numberResponse
                } catch (e: Exception) {
                    _progress.value = false
                    _errorMessage.value = e.message
                }


            }
        }
    }

    private fun stringToLong(): Long? {
        return try {
            userNumber.toLong()
        } catch (e: NumberFormatException) {
            _error.value = R.string.error_number_message
            null
        }
    }

    fun onItemClicked(str: String?) {
        _navigate.value = str
    }

    fun onLongClickListener(numberResponse: NumberResponse) {
        _removedNumber.value = numberResponse
    }

    fun clearNavigation() {
        _navigate.value = null
    }

    fun clearNumber() {
        _newNumber.value = null
    }

    fun clearRemoveNumberState() {
        _removedNumber.value = null
    }
}