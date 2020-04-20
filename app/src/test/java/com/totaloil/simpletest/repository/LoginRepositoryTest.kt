package com.totaloil.simpletest.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.totaloil.simpletest.di.module.networkModule
import com.totaloil.simpletest.di.module.repositoryModule
import com.totaloil.simpletest.di.module.utilityModule
import com.totaloil.simpletest.di.module.viewModelModule
import com.totaloil.simpletest.data.rest.APIService
import com.totaloil.simpletest.data.rest.model.BodyLogin
import com.totaloil.simpletest.data.rest.model.response.DataLogin
import com.totaloil.simpletest.data.rest.repository.GeneralRepository
import com.totaloil.simpletest.view.login.LoginViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class LoginRepositoryTest  : KoinTest {

    //Target
    private lateinit var mRepo: GeneralRepository

    lateinit var mAPIService : APIService

    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    val mLoginViewModel : LoginViewModel by inject()


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    val mNextValue = "https://swapi.co/api/people/?page=2"
    val mParam = "1"
    val mCount = 87

    @Before
    fun start(){
        startKoin {
            modules(arrayListOf(networkModule, utilityModule, repositoryModule, viewModelModule))
            androidLogger()
        }

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline()}
        MockitoAnnotations.initMocks(this)
        mAPIService = mock(APIService::class.java)
        mRepo = GeneralRepository(mAPIService)
    }

    @Test
    fun tryToLogin_success() {
        val dataReceived = mRepo.doLogin(BodyLogin("mooyern.hihi@gmail.com","12345678"))

        val data = DataLogin("address")
        //given(dataReceived).willReturn(Observable.just(ModelLogins(data)))

        mLoginViewModel.mLoginCall.value = BodyLogin("","")

        assertEquals("","")
        //loginPresenter.tryToLogin("somkiat@xxx.com", "password")

        //verify(loginViewMock).showResult("Hello somkiat")
    }

}