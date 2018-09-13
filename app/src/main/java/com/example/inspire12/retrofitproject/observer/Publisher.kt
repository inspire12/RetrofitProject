package com.example.inspire12.retrofitproject.observer

interface Publisher {
    fun add(observer: Observer)
    fun delete(observer: Observer)
    fun notifyObserver()
}