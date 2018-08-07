package com.example.inspire12.retrofitproject.model.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(position: Int, listener: (Int)-> Unit){
        itemView.setOnClickListener{listener(position)}
    }
    fun unbind(){
        itemView.setOnClickListener{}
    }
}