package com.example.inspire12.retrofitproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.inspire12.retrofitproject.Model.Photo
import com.example.inspire12.retrofitproject.Utils.CustomLog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    //lateinit var getData : ArrayList<Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //getData = intent.getParcelableArrayListExtra(MainActivity.intent_list)
            CustomLog.d(intent.getIntExtra(MainActivity.intent_index, 0).toString())
       }
}
