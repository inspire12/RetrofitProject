package com.example.inspire12.retrofitproject.bind

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.inspire12.retrofitproject.dataModel.Photo
import kotlinx.android.synthetic.main.activity_main.*

/* builder 패턴 */
class BindFactory{
    var adapter: RecyclerView.Adapter<ViewHolder>? = null;

    fun bind(responsePhotoList: ArrayList<Photo>?, rvDataList: RecyclerView, baseContext: Context ){
        if(adapter == null){
            rvDataList.setHasFixedSize(true)
            rvDataList.layoutManager = LinearLayoutManager(baseContext)
            adapter = MyAdapter(responsePhotoList, baseContext)
            rvDataList.adapter = adapter
        }else{
            adapter!!.notifyDataSetChanged()
        }
    }
}