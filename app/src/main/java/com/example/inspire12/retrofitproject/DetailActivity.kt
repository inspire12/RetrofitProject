package com.example.inspire12.retrofitproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.widget.ImageView
import com.example.inspire12.retrofitproject.Model.Photo
import com.example.inspire12.retrofitproject.Utils.CustomLog

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var getData : ArrayList<Photo>
    var getIndex : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //getData = intent.getParcelableArrayListExtra(MainActivity.intent_list)
        getIndex = intent.getIntExtra(MainActivity.intent_index, 0)
        getData = intent.getParcelableArrayListExtra(MainActivity.intent_list)
        CustomLog.d(getIndex.toString())


        viewPager.apply{

            adapter = object: FragmentStatePagerAdapter(supportFragmentManager){
                override fun getItem(position: Int): Fragment {
                    return DetailPagerFragment.newInstance(getData, position)
                }

                override fun getCount(): Int {
                    return getData.size
                }
            }
            setCurrentItem(getIndex)
        }
        indicator.attachToPager(viewPager)
        //tlPagerIndicator.setPadding(tlPagerIndicator.paddingLeft, viewPager.findViewById<ImageView>(R.id.ivImage2).height + 20, tlPagerIndicator.paddingRight, tlPagerIndicator.paddingBottom)

        //tlPagerIndicator.tabMode = MODE_SCROLLABLE

    }
}
