package com.example.inspire12.retrofitproject.view.main

import com.example.inspire12.retrofitproject.model.DemoData
import com.example.inspire12.retrofitproject.model.Photo
import java.util.ArrayList

class MessageEvent{
    var message : String? = null
    var data:  ArrayList<Photo>? = null
    constructor(data: ArrayList<Photo>){
        this.data = data
    }
}