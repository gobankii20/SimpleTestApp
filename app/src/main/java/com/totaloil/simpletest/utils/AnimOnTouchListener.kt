package com.totaloil.simpletest.utils

import com.totaloil.simpletest.utils.attribute.timeHandler.LogoutTimeHandler

class DetechOnTouchListenerImp(param: Any) {

    private var mLogoutTimeHandler = LogoutTimeHandlerImp()

    private var onClickTouch = param as LogoutTimeHandler

    init {
        onCreateInstantOnTouchTimeInterval()
    }

    private fun onCreateInstantOnTouchTimeInterval() {
        mLogoutTimeHandler.onCreateTimeInterVal(object : LogoutTimeHandler {
            override fun onDetechUserNotTouch() {
                onClickTouch.onDetechUserNotTouch()
            }
        })
    }

    fun onStartTimeInterval(){
        mLogoutTimeHandler.onCancelTimeInterval()
        mLogoutTimeHandler.onStartTimeInterval()
    }
}