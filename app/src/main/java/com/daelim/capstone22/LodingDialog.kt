package com.daelim.capstone22

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable

class LodingDialog
    constructor(context: Context) : Dialog(context){

        init {
            setCanceledOnTouchOutside(false)

            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            setContentView(R.layout.activity_loding)
        }

    }