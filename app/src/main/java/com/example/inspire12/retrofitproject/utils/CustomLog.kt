package com.example.inspire12.retrofitproject.utils


import android.util.Log
import com.example.inspire12.retrofitproject.BuildConfig


object CustomLog{
    val TAG = "Log"
    fun d(msg:String){
        if(BuildConfig.DEBUG){
            Log.d(TAG, msg)
        }
    }
    fun cd(tag: String, msg:String){
        if(BuildConfig.DEBUG){
            Log.d(tag, msg)
        }
    }
}