package com.example.inspire12.retrofitproject.bind

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_layout.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val tvTitle = itemView.tvTitle
        val tvDate = itemView.tvDate
        val tvSize = itemView.tvSize
        val tvLink = itemView.tvLink

    fun bind(position: Int, listener: (Int)-> Unit){
        itemView.setOnClickListener{listener(position)}
    }
    fun unbind(){
        itemView.setOnClickListener{}
    }
}