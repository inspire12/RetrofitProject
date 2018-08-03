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
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private companion object {
        val VIEW_MAIN = 0
        val VIEW_SUB = 1
    }

    private val baseUrl = "http://demo2587971.mockable.io/"
    private lateinit var responseData: JsonObject
    private lateinit var responsePhotoist: List<Photo>

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
                responsePhotoist = body!!.photos!!
                rvDataList.layoutManager = LinearLayoutManager(baseContext)

                rvDataList.adapter = MyAdapter(responsePhotoist, baseContext)
            }
        })
    }

    class MyAdapter(items: List<Photo>, context: Context) : RecyclerView.Adapter<MainActivity.ViewHolder>() {

        val items = items
        val context = context

        override fun getItemViewType(position: Int): Int {
            if (items[position].height == null) {
                return VIEW_SUB
            } else {
                return VIEW_MAIN
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivity.ViewHolder {
            var res: Int = R.layout.item_layout

            if (viewType != VIEW_MAIN) {
                res = R.layout.item_layout_sub
            }
            val v = LayoutInflater.from(parent.context).inflate(res, parent, false)
            return ViewHolder(v)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: MainActivity.ViewHolder, position: Int) {
            when (getItemViewType(position)) {
                VIEW_MAIN -> {
                    holder.itemView.tvTitle.setText(items[position].title)
                    holder.itemView.tvDate.setText(items[position].dateTaken)
                    if (items[position].width != null)
                        holder.itemView.tvSize.setText(String.format("%s * %s", items[position].width, items[position].height))
                    holder.itemView.tvLink.setText(items[position].url)
                    Picasso.with(context)
                            .load(items[position].url).placeholder(R.drawable.empty).error(R.drawable.empty)
                            .centerCrop()
                }
                VIEW_SUB -> {
                    holder.itemView.tvTitle.setText(items[position].title)
                    holder.itemView.tvDate.setText(items[position].dateTaken)
                }
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
