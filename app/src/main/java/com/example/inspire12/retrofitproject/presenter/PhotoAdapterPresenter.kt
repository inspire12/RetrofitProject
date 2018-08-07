package com.example.inspire12.retrofitproject.presenter

import com.example.inspire12.retrofitproject.MainActivity
import com.example.inspire12.retrofitproject.model.Photo
import com.example.inspire12.retrofitproject.R

class PhotoAdapterPresenter{
    var view: View? = null
    private var items = mutableListOf<Photo>()

    fun onDataChange(inputItems: List<Photo>){
        items.forEach{
            if(!items.contains(it)){
                items.add(it)
            }
        }
        view?.notifyAdapter() // 리스너
    }
    fun getCount(): Int = items.size

    fun getItemAt(position: Int) = items[position]
    // infix??
    infix fun itemAtPosition(position: Int): Photo = items[position]

    fun viewType(height: String?): Int {
        if (height == null) {
            return MainActivity.VIEW_SUB
        } else {
            return MainActivity.VIEW_MAIN
        }
    }
    fun getViewRes(viewType:Int): Int{
        var res: Int = R.layout.item_layout

        if (viewType != MainActivity.VIEW_MAIN) {
            res = R.layout.item_layout_sub
        }
        return res
    }
    // 비동기 처리
    interface View{
        fun notifyAdapter()
    }
}
