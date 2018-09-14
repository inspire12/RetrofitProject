package com.example.inspire12.retrofitproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.inspire12.retrofitproject.dataModel.Photo
import com.example.inspire12.retrofitproject.utils.CustomLog
import com.example.inspire12.retrofitproject.view.MainActivity

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    var getIndex : Int = 0
    lateinit var getData : ArrayList<Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //getData = intent.getParcelableArrayListExtra(MainActivity.intent_list)
        getIndex = intent.getIntExtra(MainActivity.intent_index, 0)
        getData = intent.getParcelableArrayListExtra(MainActivity.intent_list)


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
    }
}
