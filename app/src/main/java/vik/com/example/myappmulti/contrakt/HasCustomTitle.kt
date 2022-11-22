package vik.com.example.myappmulti.contrakt

import androidx.annotation.StringRes

interface HasCustomTitle {

    @StringRes
    fun getTitleRes() : Int

}