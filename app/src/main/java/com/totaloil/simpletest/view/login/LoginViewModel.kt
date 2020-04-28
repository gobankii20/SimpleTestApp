package com.totaloil.simpletest.view.login

import androidx.databinding.ObservableField
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.totaloil.simpletest.data.remote.model.BodyLogin
import com.totaloil.simpletest.data.remote.repository.GeneralRepository
import com.totaloil.simpletest.utils.SingleLiveData

class LoginViewModel constructor(private val generalRepository: GeneralRepository) : ViewModel() {

    var etUserName = ObservableField("")

    var etPassword = ObservableField("")

    var mLoginCall = SingleLiveData<BodyLogin>()

    val mResponseLogin = Transformations.switchMap(mLoginCall) {
        generalRepository.doLogin(it)
    }

    fun onClickLogin() {
        mLoginCall.value = BodyLogin(etUserName.get() ?: "", etPassword.get() ?: "")
    }

}