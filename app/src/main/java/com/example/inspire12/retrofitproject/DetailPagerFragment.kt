package com.example.inspire12.retrofitproject

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.inspire12.retrofitproject.dataModel.Photo
import com.squareup.picasso.Picasso


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * oncreate 데이터를 받는 역할
 * newInstance 에서 fragment 생성
 * onCreate (데이터 받음) -> onCreateView -> onViewCreated (데이터 binding)
 */

class DetailPagerFragment : Fragment() {

    lateinit var getListData: ArrayList<Photo>
    var getIndex: Int = 0

    /**
     * Activity 에서 데이터를 받음
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            getListData = it.getParcelableArrayList(ARG_PARAM1)
            getIndex = it.getInt(ARG_PARAM2)
        }
    }

    /**
     * 컴포넌트 View을 Activity에 생성하고
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data = getListData[getIndex]

        Picasso.with(context).load(data.url).into(view.findViewById<ImageView>(R.id.ivImage2))
        view.apply {
            findViewById<TextView>(R.id.tvDetailTitle).setText(data.title)
            findViewById<TextView>(R.id.tvDetailDate).setText(data.dateTaken)
            findViewById<TextView>(R.id.tvDetailLink).setText(data.url)
            if (data.width != null && data.height != null)
                findViewById<TextView>(R.id.tvDetailSize).setText(String.format("%s * %s", data.width, data.height))
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(getListData: ArrayList<Photo>, index: Int) =
                DetailPagerFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(ARG_PARAM1, getListData)
                        putInt(ARG_PARAM2, index)
                    }
                }
    }
}
