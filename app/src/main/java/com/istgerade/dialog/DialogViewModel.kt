package com.istgerade.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istgerade.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DialogViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _ad = MutableLiveData<String>()

    val ad: LiveData<String>
        get() = _ad

    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress


    fun translateAd(ad: String?) {
        viewModelScope.launch {
            _progress.value = true
            val translatedAd = ad?.let { repository.translate(it) }
            translatedAd?.translatedText.let { _ad.value = it }
            _progress.value = false
        }
    }
}