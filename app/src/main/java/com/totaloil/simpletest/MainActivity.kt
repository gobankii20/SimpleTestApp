package com.totaloil.simpletest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.totaloil.simpletest.remote.model.ModelLogin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var mData : ModelLogin? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        onBundleData()
    }

    private fun onBundleData() {
         mData = intent?.getParcelableExtra("data")

        initSetText()
    }

    @SuppressLint("SetTextI18n")
    private fun initSetText() {
        tvFullName.text = "${mData?.first_name} ${mData?.last_name}"
        tvRole.text = mData?.role

        Glide.with(this)
            .load(mData?.picture)
            .into(ivProfile)
    }

    fun onSetData(data:String){
        
    }
}
