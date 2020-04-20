package com.totaloil.simpletest.view.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.totaloil.simpletest.R
import com.totaloil.simpletest.data.rest.model.Status
import com.totaloil.simpletest.databinding.ActivityLoginBinding
import com.totaloil.simpletest.utils.DetechOnTouchListenerImp
import com.totaloil.simpletest.utils.attribute.timeHandler.LogoutTimeHandler
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(),LogoutTimeHandler {

    private val viewModel : LoginViewModel by viewModel()

    private lateinit var binding: ActivityLoginBinding

    lateinit var mAnimOnTouchListenerImp: DetechOnTouchListenerImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        initViewModel()
        initView()
    }

    private fun initViewModel() {
       binding.dataViewModel = viewModel
       mAnimOnTouchListenerImp = DetechOnTouchListenerImp(this)
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(ev!!.action == MotionEvent.ACTION_UP)
            mAnimOnTouchListenerImp.onStartTimeInterval()
        return super.dispatchTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        viewModel.dataResponse.observe(this, Observer {
            binding.loadResource = it
            when (it.status) {
                Status.SUCCESS -> {
//                    val intentMain = Intent(this, MainActivity::class.java)
//                    intentMain.putExtra("data", it.data)
//                    startActivity(intentMain)
                }
                Status.ERROR -> binding.tvError.text = it.message
                Status.LOADING -> TODO()
            }
        })
    }

    override fun onDetechUserNotTouch() {
        Log.i("MotionEvent","Logout")
    }

}
