package com.example.inspire12.retrofitproject.view.main.adapter

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inspire12.retrofitproject.view.detail.DetailActivity
import com.example.inspire12.retrofitproject.view.main.MainActivity
import com.example.inspire12.retrofitproject.R
import com.example.inspire12.retrofitproject.model.Photo
import com.example.inspire12.retrofitproject.view.main.adapter.viewholder.PhotoViewHolder
import com.example.inspire12.retrofitproject.view.main.presenter.PhotoAdapterContract
import com.example.inspire12.retrofitproject.view.main.presenter.PhotoAdapterPresenter
import com.example.inspire12.retrofitproject.view.main.presenter.PhotoViewHolderPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*
import java.util.ArrayList

class PhotoRecyclerAdapter(val items: List<Photo>, val context: Context)
    : RecyclerView.Adapter<PhotoViewHolder>(), PhotoAdapterContract.View {

    private var mPresenter: PhotoAdapterContract.Presenter? = null

    override fun setPresenter(presenter: PhotoAdapterContract.Presenter) {
        mPresenter = presenter
    }


    val mAdapterPresenter = PhotoAdapterPresenter()

    init {
        mAdapterPresenter.view = this
    }

    //
    override fun getItemViewType(position: Int): Int {
        if (items[position].url == null) {
            return MainActivity.VIEW_SUB
        } else {
            return MainActivity.VIEW_MAIN
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(mAdapterPresenter.getViewRes(viewType), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MainActivity.VIEW_MAIN -> {
                //초기화
                holder.showProgress()
                holder.itemView.tvTitle.setText(items[position].title)
                holder.itemView.tvDate.setText(items[position].dateTaken)

                // 2. Presenter에 이벤트 전달
                holder.mPresenter?.setSize(items[position].width, items[position].height)

                holder.itemView.tvLink.setText(items[position].url)
                holder.bind(position, { item: Int ->
                    itemClick(position)
                })

                Picasso.with(context)
                        .load(items[position].url).placeholder(R.drawable.empty).error(R.drawable.empty)
                        .into(holder.itemView.ivImage, object : com.squareup.picasso.Callback {
                            override fun onSuccess() {
                                // 1. 이벤트 발생
                                holder.hideProgress()
                            }

                            override fun onError() {

                            }
                        })
            }
            MainActivity.VIEW_SUB -> {
                holder.itemView.tvTitle.setText(items[position].title)
                holder.itemView.tvDate.setText(items[position].dateTaken)
                holder.bind(position, { item: Int ->
                    itemClick(position)
                })
            }
        }
    }

    fun itemClick(position: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putParcelableArrayListExtra(MainActivity.intent_list, items as ArrayList<out Parcelable>)
        intent.putExtra(MainActivity.intent_index, position)
        context.startActivity(intent)
    }
}