package com.example.inspire12.retrofitproject.view.main.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.inspire12.retrofitproject.R
import com.example.inspire12.retrofitproject.view.main.presenter.PhotoViewHolderContract
import com.example.inspire12.retrofitproject.view.main.presenter.PhotoViewHolderPresenter
import kotlinx.android.synthetic.main.item_layout.view.*


class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), PhotoViewHolderContract.View {

    var mPresenter: PhotoViewHolderContract.Presenter? = null

    init {
        mPresenter = PhotoViewHolderPresenter(this)
    }
    override fun showProgress() {
        itemView.tvTitle.visibility = View.INVISIBLE
        itemView.tvDate.visibility = View.INVISIBLE
        itemView.tvSize.visibility = View.INVISIBLE
        itemView.tvLink.visibility = View.INVISIBLE
    }

    override fun hideProgress() {
        itemView.tvTitle.visibility = View.VISIBLE
        itemView.tvDate.visibility = View.VISIBLE
        itemView.tvSize.visibility = View.VISIBLE
        itemView.tvLink.visibility = View.VISIBLE
    }

    fun bind(position: Int, listener: (Int)-> Unit){
        itemView.setOnClickListener{listener(position)}
    }
    fun unbind(){
        itemView.setOnClickListener{}
    }


    override fun setPresenter(presenter: PhotoViewHolderContract.Presenter) {
        mPresenter = presenter
    }


    override fun onSetSize(width: String?, height: String?) {
        // 5.전달 받아 UI 갱신
        itemView.findViewById<TextView>(R.id.tvSize).setText(String.format("%s * %s", width, height))
    }

    override fun onSetImage(src: String) {

    }


}