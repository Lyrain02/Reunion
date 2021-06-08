package com.example.mytest.ui.square

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SquareViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "我的信息"
    }
    val text: LiveData<String> = _text
}