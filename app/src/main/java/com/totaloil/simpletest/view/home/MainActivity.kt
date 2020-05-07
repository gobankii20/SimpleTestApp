package com.totaloil.simpletest.view.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.totaloil.simpletest.R
import com.totaloil.simpletest.data.remote.model.Status
import com.totaloil.simpletest.data.remote.model.response.DataListNews
import com.totaloil.simpletest.databinding.ActivityMainBinding
import com.totaloil.simpletest.utils.DialogPresenter
import com.totaloil.simpletest.utils.attribute.timeHandler.LogoutTimeHandler
import com.totaloil.simpletest.utils.hideSystemMenuBottom
import com.totaloil.simpletest.utils.timeInterval.DetechOnTouchListenerImp
import com.totaloil.simpletest.view.login.LoginActivity
import com.totaloil.simpletest.view.newsDetail.NewDetailActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class MainActivity : AppCompatActivity(), LogoutTimeHandler {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    private val mDialogPresenter: DialogPresenter by inject { parametersOf(applicationContext) }

    private lateinit var mNewsAdapter: NewsAdapter

    private var mListNews = ArrayList<DataListNews>()

    private val mAnimOnTouchListenerImp by lazy {
        DetechOnTouchListenerImp(
            this
        )
    }

    companion object {
        fun newInstance(context: Context) {
            val intentMain = Intent(context, MainActivity::class.java)
            context.startActivity(intentMain)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.hideSystemMenuBottom()
        setDataListNews()

    }

    private fun setDataListNews() {
        mNewsAdapter = NewsAdapter(mListNews, viewModel.onClickListNews)

        binding.rvLisNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mNewsAdapter
            mNewsAdapter.notifyDataSetChanged()
        }
    }

    private fun initViewModel() {
        onSubScriptListNews()
        onClickListener()
    }

    private fun onClickListener() {
        viewModel.onClickListNews.observe(this, Observer { data ->
            NewDetailActivity.newInstance(this, data)
        })
    }

    private fun onSubScriptListNews() {
        viewModel.mResponseNews.observe(this, Observer {
            binding.loadResource = it
            when (it.status) {
                Status.SUCCESS -> {
                    mListNews.clear()
                    it.data?.data?.let { data -> mListNews.addAll(data) }
                    mNewsAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    mDialogPresenter.dialogMessage(
                        resources.getString(R.string.title_dialog),
                        it.message
                    )
                }
                else -> print("no event")
            }
        })
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (ev!!.action == MotionEvent.ACTION_UP)
            mAnimOnTouchListenerImp.onStartTimeInterval()
        return super.dispatchTouchEvent(ev)
    }

    override fun onDetechUserNotTouch() {
        LoginActivity.newInstance(this)
    }
}
