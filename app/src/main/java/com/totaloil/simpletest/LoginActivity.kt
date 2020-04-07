package com.totaloil.simpletest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.totaloil.simpletest.remote.connect.Status
import com.totaloil.simpletest.remote.model.BodyLogin
import com.totaloil.simpletest.remote.repository.GeneralRepository
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {
        btLogin.setOnClickListener {

            viewProgressLoad(true)
            val dataResponse = GeneralRepository.onLogin(BodyLogin(etUserName.text.toString(),etPassword.text.toString()))

            dataResponse.observe(this, Observer {
                viewProgressLoad(Status.SUCCESS == it.status)
                when (it.status) {
                    Status.SUCCESS -> {
                        val intentMain = Intent(this,MainActivity::class.java)
                        intentMain.putExtra("data",it.data)
                        startActivity(intentMain)
                    }
                    Status.ERROR -> tvError.text = it.message
                    Status.LOADING -> TODO()
                }
            })
        }
    }

    private fun viewProgressLoad(isLoading: Boolean) {
        if (!isLoading){
            pbLoad.visibility = View.GONE
        }else{
            pbLoad.visibility = View.VISIBLE
        }
    }


}
