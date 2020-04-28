package com.totaloil.simpletest.view.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.totaloil.simpletest.R
import com.totaloil.simpletest.data.remote.model.Status
import com.totaloil.simpletest.databinding.ActivityLoginBinding
import com.totaloil.simpletest.utils.DialogPresenter
import com.totaloil.simpletest.view.home.MainActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(){

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModel()

    private val dialogPresenter: DialogPresenter by inject { parametersOf(applicationContext) }

    companion object{
        fun newInstance(context: Context){
            val intentLogin = Intent(context,LoginActivity::class.java)
            context.startActivity(intentLogin)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initViewModel()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

    }

    private fun initViewModel() {
        binding.dataViewModel = viewModel
        onSubScriptLogin()
    }

    private fun onSubScriptLogin() {
        viewModel.mResponseLogin.observe(this, Observer {
            binding.loadResource = it

            when (it.status) {
                Status.SUCCESS -> {
                    MainActivity.newInstance(this)
                }
                Status.ERROR -> {
                    dialogPresenter.dialogMessage(resources.getString(R.string.title_dialog), it.message){
                        MainActivity.newInstance(this)
                    }
                }
                else -> print("no event")
            }
        })
    }

}
