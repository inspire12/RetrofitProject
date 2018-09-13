package com.example.inspire12.retrofitproject.observer

import com.example.inspire12.retrofitproject.dataModel.Photo

interface Observer {
    public fun update(responseList:ArrayList<Photo>)
}