package com.totaloil.simpletest.view.newsDetail

import androidx.databinding.ObservableField
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.totaloil.simpletest.data.remote.repository.GeneralRepository
import com.totaloil.simpletest.utils.SingleLiveData

class NewDetailViewModel constructor(generalRepository: GeneralRepository) : ViewModel() {

    val isSelectedLike = ObservableField(false)

    val mNewsId = ObservableField("0")

    private val mLikeCall = SingleLiveData<Void>()

    val mResponseLike = Transformations.switchMap(mLikeCall){
        generalRepository.doLikeNews(mNewsId.get()?:"0")
    }
    fun onClickSelectedLike(){
        mLikeCall.call()
    }
}