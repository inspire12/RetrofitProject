package com.example.inspire12.retrofitproject.view.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.inspire12.retrofitproject.R
import com.example.inspire12.retrofitproject.model.Photo
import com.example.inspire12.retrofitproject.utils.CustomLog
import com.example.inspire12.retrofitproject.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var getData : ArrayList<Photo>
    var getIndex : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

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

        pageIndicatorView.attachToPager(viewPager)
//        tlPagerIndicator.tabMode = MODE_SCROLLABLE
//        tlPagerIndicator.setScrollPosition(getIndex, 0F, true)
//        tlPagerIndicator.setupWithViewPager(viewPager, false)

    }
}
