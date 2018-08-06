package com.example.inspire12.retrofitproject.Presenter

class PhotoViewHolderPresenter {
    var adapterPresenter: PhotoAdapterPresenter? = null
    var position: Int? = null
    var view: View? = null

    fun bind(){
        if(position != null && adapterPresenter != null){
            val items = adapterPresenter!!.getItemAt(position!!)
            view?.setTitle(items.title!!)
            view?.setDate(items.dateTaken!!)
            view?.setSize(items.width!!, items.height!!)
            view?.setImage(items.url!!)
        }
    }

    interface View {
        fun setTitle(title: String)
        fun setDate(date: String)
        fun setLink(url: String)
        fun setSize(width:String, height: String)
        fun setImage(src: String)
    }
}