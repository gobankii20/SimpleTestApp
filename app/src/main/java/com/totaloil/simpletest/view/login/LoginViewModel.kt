package com.totaloil.simpletest.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.totaloil.simpletest.data.rest.model.BodyLogin
import com.totaloil.simpletest.data.rest.model.Resource
import com.totaloil.simpletest.data.rest.model.response.ModelLogins
import com.totaloil.simpletest.data.rest.repository.GeneralRepository
import com.totaloil.simpletest.utils.SingleLiveData

class LoginViewModel constructor(val generalRepository: GeneralRepository) : ViewModel() {

    var mLoginCall = SingleLiveData<BodyLogin>()

    val dataResponse: LiveData<Resource<ModelLogins>> = Transformations.switchMap(mLoginCall) {
        generalRepository.doLogin(it)
    }

}