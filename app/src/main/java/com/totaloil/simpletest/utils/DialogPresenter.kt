package com.totaloil.simpletest.utils

import android.app.Dialog
import android.view.LayoutInflater
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.totaloil.simpletest.R
import com.totaloil.simpletest.databinding.DialogAlertMessageBinding

class DialogPresenter constructor(private var fragmentActivity: AppCompatActivity) {

    fun dialogMessage(title: String, text: String?) {
        val dialog = getDialog()
        dialog.setCanceledOnTouchOutside(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val binding: DialogAlertMessageBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(fragmentActivity),
                R.layout.dialog_alert_message,
                null,
                false
            )
        dialog.setContentView(binding.root)

        binding.title = title
        binding.text = text

        binding.tvOkey.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    fun dialogMessage(title: String, text: String?, callBack: (Boolean) -> Unit) {
        val dialog = getDialog()
        dialog.setCanceledOnTouchOutside(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val binding: DialogAlertMessageBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(fragmentActivity),
                R.layout.dialog_alert_message,
                null,
                false
            )
        dialog.setContentView(binding.root)

        binding.title = title
        binding.text = text

        binding.tvOkey.setOnClickListener {
            dialog.dismiss()
            callBack.invoke(true)
        }

        dialog.show()
    }


    private fun getDialog(): Dialog = Dialog(fragmentActivity)
}