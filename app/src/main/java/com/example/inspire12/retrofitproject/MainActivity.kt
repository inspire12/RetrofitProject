package com.example.inspire12.retrofitproject

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inspire12.retrofitproject.Model.DemoData
import com.example.inspire12.retrofitproject.Model.Photo
import com.example.inspire12.retrofitproject.Utils.CustomLog
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val baseUrl = "http://demo2587971.mockable.io/"
    lateinit var responseData: JsonObject
    lateinit var responsePhotoist: List<Photo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDataList.setHasFixedSize(true)

        var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                .build()

        var service: DemoService = retrofit.create(DemoService::class.java)

        var request: Call<DemoData> = service.getData()

        request.enqueue(object : Callback<DemoData> {
            override fun onFailure(call: Call<DemoData>?, t: Throwable?) {
                CustomLog.d(t.toString())
            }

            override fun onResponse(call: Call<DemoData>?, response: Response<DemoData>?) {
                CustomLog.d("success")
                CustomLog.d(response!!.body()!!.page.toString())
                // 세팅
                var body = response.body()
                responsePhotoist = body!!.photos!!
                rvDataList.layoutManager = LinearLayoutManager(baseContext)

                rvDataList.adapter = MyAdapter(responsePhotoist)
            }
        })
    }

    class MyAdapter(items: List<Photo>) : RecyclerView.Adapter<MainActivity.ViewHolder>() {

        val items = items

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivity.ViewHolder {
            var res :Int = R.layout.item_layout


            var v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

            var holder: ViewHolder = ViewHolder(v)
            return holder
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: MainActivity.ViewHolder, position: Int) {

            holder.itemView.tvTitle.setText(items[position].title)
            holder.itemView.tvDate.setText(items[position].dateTaken)
            if(items[position].width != null)
                holder.itemView.tvSize.setText(String.format("%s * %s",items[position].width, items[position].height))
            holder.itemView.tvLink.setText(items[position].url)

        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
