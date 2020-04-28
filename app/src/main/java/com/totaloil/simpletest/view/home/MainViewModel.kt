package com.totaloil.simpletest.view.home

import androidx.lifecycle.ViewModel
import com.totaloil.simpletest.data.remote.model.response.DataListNews
import com.totaloil.simpletest.data.remote.repository.GeneralRepository
import com.totaloil.simpletest.utils.SingleLiveData

class MainViewModel constructor(generalRepository: GeneralRepository):ViewModel() {

    val mResponseNews = generalRepository.getListNews()

    val onClickListNews = SingleLiveData<DataListNews>()
}