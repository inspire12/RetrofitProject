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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var res: Int = R.layout.item_layout

        if (viewType != MainActivity.VIEW_MAIN) {
            res = R.layout.item_layout_sub
        }
        val v = LayoutInflater.from(parent.context).inflate(res, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MainActivity.VIEW_MAIN -> {
                initPreBinding(holder)
                Picasso.with(context)
                        .load(items!![position].url).placeholder(R.drawable.empty).error(R.drawable.empty)
                        .into(holder.itemView.ivImage)
                initTextBinding(holder, items[position])

            }
            MainActivity.VIEW_SUB -> {
                holder.tvTitle.setText(items!![position].title)
                holder.tvDate.setText(items[position].dateTaken)
            }
        }

        // 공통 처리
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                itemClick(holder.adapterPosition)
            }
        })
    }

    override fun getItemViewType(position: Int): Int {
        if (items!![position].height == null) {
            return MainActivity.VIEW_SUB
        } else {
            return MainActivity.VIEW_MAIN
        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    private fun initPreBinding(holder:ViewHolder){
        holder.tvTitle.visibility = View.INVISIBLE
        holder.tvDate.visibility = View.INVISIBLE
        holder.tvSize.visibility = View.INVISIBLE
        holder.tvLink.visibility = View.INVISIBLE
        holder.unbind()
    }
    private fun initTextBinding(holder: ViewHolder, photo:Photo){
        holder.tvTitle.visibility = View.VISIBLE
        holder.tvDate.visibility = View.VISIBLE
        holder.tvSize.visibility = View.VISIBLE
        holder.tvLink.visibility = View.VISIBLE
        holder.tvTitle.setText(photo.title)
        holder.tvDate.setText(photo.dateTaken)
        if (photo.width != null)
            holder.tvSize.setText(String.format("%s * %s", photo.width, photo.height))
        holder.itemView.tvLink.setText(photo.url)
    }

    fun itemClick(position: Int){
        val intent = Intent(context, DetailActivity::class.java)
        intent.putParcelableArrayListExtra(MainActivity.intent_list, items as ArrayList<out Parcelable>)
        intent.putExtra(MainActivity.intent_index, position)
        context.startActivity(intent)
    }
}