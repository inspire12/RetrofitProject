package com.example.inspire12.retrofitproject.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.inspire12.retrofitproject.R
import com.example.inspire12.retrofitproject.dataModel.Photo
import com.example.inspire12.retrofitproject.bind.BindFactory
import com.example.inspire12.retrofitproject.loadData.Network
import com.example.inspire12.retrofitproject.observer.Observer
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

// view 만 처리
class MainActivity : AppCompatActivity(), Observer {


    companion object {
        val VIEW_MAIN = 0
        val VIEW_SUB = 1
        @JvmStatic
        val intent_list = "LIST"
        val intent_index = "INDEX"
    }

    private val baseUrl = "http://demo2587971.mockable.io/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 목표 main의 길이를 짧게하기 위한 observer pattern 사용
         * 1. 데이터를 불러오는 Network 객체를 publisher로 등록, 데이터 불러오면 끝나면 등록한 모든 observer에게 noti
         * 2. view를 담당할 mainActivity를 observer로 만들고 Network publisher에 등록
         * 3. 데이터 로드
         * 4. 데이터 로드가 끝나면 notifyObserver로 등록된 observer 객체(MainActivity) 의 update를 실행
         * 5. recyclerview 와 binding 생성하는 부분을 factory method로 분리, 당장은 의미 없지만 추후에 list 타입이 바뀔 때 유연하게 처리
         */

        val network: Network = Network()
        network.add(this)
        network.loadData(baseUrl) // observer에 noti 포함
        // load 끝나면 network pulisher가 통해 update 실행
    }

    override fun update(responseList: ArrayList<Photo>) {
        val bindFactory: BindFactory = BindFactory()
        bindFactory.bind(responseList, rvDataList, baseContext)
    }
}
