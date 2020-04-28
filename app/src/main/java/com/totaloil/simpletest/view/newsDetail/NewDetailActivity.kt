package com.totaloil.simpletest.view.newsDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.totaloil.simpletest.R
import com.totaloil.simpletest.data.remote.model.Status
import com.totaloil.simpletest.data.remote.model.response.DataListNews
import com.totaloil.simpletest.databinding.ActivityNewDetailBinding
import com.totaloil.simpletest.utils.DateManage
import com.totaloil.simpletest.utils.DialogPresenter
import com.totaloil.simpletest.utils.attribute.timeHandler.LogoutTimeHandler
import com.totaloil.simpletest.utils.timeInterval.DetechOnTouchListenerImp
import com.totaloil.simpletest.view.login.LoginActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class NewDetailActivity : AppCompatActivity(), LogoutTimeHandler {

    private lateinit var binding: ActivityNewDetailBinding

    private val viewModel: NewDetailViewModel by viewModel()

    private var mBundleDataNews: DataListNews? = null

    private val mAnimOnTouchListenerImp by lazy {
        DetechOnTouchListenerImp(
            this
        )
    }

    private val mDateManage: DateManage by inject()

    private val mDialogPresenter: DialogPresenter by inject { parametersOf(applicationContext) }

    companion object {
        private const val TAG_EXTRA = "ob_news"

        fun newInstance(context: Context, dataNewsDetail: DataListNews) {
            val intent = Intent(context, NewDetailActivity::class.java)
            intent.putExtra(TAG_EXTRA, dataNewsDetail)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initViewModel()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_new_detail)
    }

    private fun initViewModel() {
        binding.dataViewModel = viewModel
        setTextNewDetail()
        onSubScriptLikeNews()
    }

    private fun onSubScriptLikeNews() {
        viewModel.mResponseLike.observe(this, Observer {
            binding.loadResource = it
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.isSelectedLike.set(!viewModel.isSelectedLike.get()!!)
                }
                Status.ERROR -> {
                    mDialogPresenter.dialogMessage(resources.getString(R.string.title_dialog), it.message)
                }
                else -> {
                    print("no event")
                }
            }
        })
    }

    private fun setTextNewDetail() {
        mBundleDataNews = intent?.getParcelableExtra(TAG_EXTRA)

        mBundleDataNews?.let { data ->
            data.create = mDateManage.timeStampToDateFormat(data.create.toLong())
            viewModel.mNewsId.set(data.id)
            binding.dataResponse = data
        }
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
