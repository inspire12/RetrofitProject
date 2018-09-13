package com.example.inspire12.retrofitproject.bind

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inspire12.retrofitproject.DetailActivity
import com.example.inspire12.retrofitproject.view.MainActivity
import com.example.inspire12.retrofitproject.dataModel.Photo
import com.example.inspire12.retrofitproject.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*
import java.util.ArrayList


class MyAdapter(val items: List<Photo>?, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        if (items!![position].height == null) {
            return MainActivity.VIEW_SUB
        } else {
            return MainActivity.VIEW_MAIN
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var res: Int = R.layout.item_layout

        if (viewType != MainActivity.VIEW_MAIN) {
            res = R.layout.item_layout_sub
        }
        val v = LayoutInflater.from(parent.context).inflate(res, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MainActivity.VIEW_MAIN -> {
                holder.itemView.tvTitle.visibility = View.INVISIBLE
                holder.itemView.tvDate.visibility = View.INVISIBLE
                holder.itemView.tvSize.visibility = View.INVISIBLE
                holder.itemView.tvLink.visibility = View.INVISIBLE
                holder.unbind()


                Picasso.with(context)
                        .load(items!![position].url).placeholder(R.drawable.empty).error(R.drawable.empty)
                        .into(holder.itemView.ivImage, object: com.squareup.picasso.Callback{
                            override fun onSuccess() {

                            }
                            override fun onError() {

                            }
                        })

                holder.itemView.tvTitle.visibility = View.VISIBLE
                holder.itemView.tvDate.visibility = View.VISIBLE
                holder.itemView.tvSize.visibility = View.VISIBLE
                holder.itemView.tvLink.visibility = View.VISIBLE
                holder.itemView.tvTitle.setText(items[position].title)
                holder.itemView.tvDate.setText(items[position].dateTaken)
                if (items[position].width != null)
                    holder.itemView.tvSize.setText(String.format("%s * %s", items[position].width, items[position].height))
                holder.itemView.tvLink.setText(items[position].url)
                holder.bind( position ,{
                    item: Int-> itemClick(position)
                })
            }
            MainActivity.VIEW_SUB -> {
                holder.itemView.tvTitle.setText(items!![position].title)
                holder.itemView.tvDate.setText(items!![position].dateTaken)
            }
        }
    }
    fun itemClick(position: Int){
        val intent = Intent(context, DetailActivity::class.java)
        intent.putParcelableArrayListExtra(MainActivity.intent_list, items as ArrayList<out Parcelable>)
        intent.putExtra(MainActivity.intent_index, position)
        context.startActivity(intent)
    }
}