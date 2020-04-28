package com.totaloil.simpletest.utils.timeInterval

import android.os.CountDownTimer
import com.totaloil.simpletest.data.locel.Constanst
import com.totaloil.simpletest.utils.attribute.timeHandler.LogoutTimeHandler

class LogoutTimeHandler {

    private lateinit var mCountDownTimer: CountDownTimer

    private var isCheckTimeRunning = false

    fun onCreateTimeInterVal(mLogoutTimeHandler: LogoutTimeHandler){
        mCountDownTimer = object: CountDownTimer(Constanst.TIME_INTERVAL, Constanst.TIME_INTERVAL_UNIT) {
            override fun onTick(millisUntilFinished: Long) {
                isCheckTimeRunning = true
            }

            override fun onFinish() {
                isCheckTimeRunning = false
                mLogoutTimeHandler.onDetechUserNotTouch()
            }
        }
    }

    fun onStartTimeInterval(){
        mCountDownTimer.start()
    }


    fun onCancelTimeInterval(){
        if (isCheckTimeRunning) {
            mCountDownTimer.cancel()
        }
    }
}