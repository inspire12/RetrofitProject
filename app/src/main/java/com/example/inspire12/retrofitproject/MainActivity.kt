package com.example.inspire12.retrofitproject

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inspire12.retrofitproject.model.DemoData
import com.example.inspire12.retrofitproject.model.Photo
import com.example.inspire12.retrofitproject.presenter.PhotoAdapterPresenter
import com.example.inspire12.retrofitproject.utils.CustomLog
import com.example.inspire12.retrofitproject.model.viewholder.PhotoViewHolder
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {



    companion object {

        val VIEW_MAIN = 0
        val VIEW_SUB = 1
        @JvmStatic
        val intent_title = "TITLE"
        val intent_url = "URL"
        val intent_date = "DATE"
        val intent_size = "SIZE"
        val intent_list = "LIST"
        val intent_index = "INDEX"
    }

    private val baseUrl = "http://demo2587971.mockable.io/"
    private lateinit var responseData: JsonObject
    private lateinit var responsePhotoList: ArrayList<Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDataList.setHasFixedSize(true)

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()

        val service: DemoService = retrofit.create(DemoService::class.java)

        val request: Call<DemoData> = service.getData()

        request.enqueue(object : Callback<DemoData> {
            override fun onFailure(call: Call<DemoData>?, t: Throwable?) {
                CustomLog.d(t.toString())
            }

            override fun onResponse(call: Call<DemoData>?, response: Response<DemoData>?) {
                CustomLog.d("success")
                CustomLog.d(response!!.body()!!.page.toString())
                // 세팅
                val body = response.body()
                responsePhotoList = body!!.photos!! as ArrayList<Photo>
                rvDataList.layoutManager = LinearLayoutManager(baseContext)

                rvDataList.adapter = MyAdapter(responsePhotoList, baseContext, responsePhotoList)
            }
        })
    }


    class MyAdapter(val items: List<Photo>, val context: Context, val responsePhotoList:ArrayList<Photo>)
        : RecyclerView.Adapter<PhotoViewHolder>(), PhotoAdapterPresenter.View {

        val mAdapterPresenter = PhotoAdapterPresenter()

        init{
            mAdapterPresenter.view = this
        }

        override fun getItemViewType(position: Int): Int {
            return mAdapterPresenter.viewType(items[position].height)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
            return PhotoViewHolder(LayoutInflater.from(parent.context).inflate(mAdapterPresenter.getViewRes(viewType), parent, false))
        }



        override fun getItemCount(): Int {
            return items.size
          }

        override fun onBindViewHolder(holder:PhotoViewHolder, position: Int) {
            when (getItemViewType(position)) {
                VIEW_MAIN -> {
                    holder.itemView.tvTitle.visibility = View.INVISIBLE
                    holder.itemView.tvDate.visibility = View.INVISIBLE
                    holder.itemView.tvSize.visibility = View.INVISIBLE
                    holder.itemView.tvLink.visibility = View.INVISIBLE
                    holder.unbind()
                    Picasso.with(context)
                            .load(items[position].url).placeholder(R.drawable.empty).error(R.drawable.empty)
                            .into(holder.itemView.ivImage, object: com.squareup.picasso.Callback{
                                override fun onSuccess() {
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
                                override fun onError() {

                                }
                            })
                }
                VIEW_SUB -> {
                    holder.itemView.tvTitle.setText(items[position].title)
                    holder.itemView.tvDate.setText(items[position].dateTaken)
                }
            }
        }

        override fun notifyAdapter() {
        }

        fun itemClick(position: Int){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putParcelableArrayListExtra(MainActivity.intent_list, responsePhotoList)
            intent.putExtra(MainActivity.intent_index, position)
            context.startActivity(intent)
        }
    }
}
