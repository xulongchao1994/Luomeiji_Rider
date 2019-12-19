package com.android.luomeiji_rider.ui.legalize

import com.android.luomeiji_rider.base.LBaseView


interface ICarLegalizeView : LBaseView {
    fun carlegalizesuccess(string: String)
    fun carlegalizeerror(string: String)
}