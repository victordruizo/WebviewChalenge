package com.example.chalenge_webview.mainFragment

import android.view.View
import android.webkit.URLUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

class MainViewModel : ViewModel() {
    private var _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String> get() = _toastMessage

    fun onCreate(){
        _toastMessage.postValue("")
    }

    fun search(url:String, view : View){
        if (URLUtil.isValidUrl(url)){
            val action = MainFragmentDirections.actionMainFragmentToWebviewFragment(url)
            view.findNavController().navigate(action)
        }else{
            _toastMessage.postValue("Invalid URL")
        }
    }
}