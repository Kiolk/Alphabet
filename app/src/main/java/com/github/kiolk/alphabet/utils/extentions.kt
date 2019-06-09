package com.github.kiolk.alphabet.utils

import android.content.res.Resources
import android.support.annotation.StringRes
import com.bluelinelabs.conductor.Controller
import java.util.*

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

fun <T> MutableList<T>.randomize() : MutableList<T>{
    val tmp = mutableListOf<T>()
    while(tmp.size != this.size){
        val item = this.get(Random().nextInt(size))
        if(!tmp.contains(item)) tmp.add(item)
    }
    return tmp
}

fun Controller.getString(@StringRes resId: Int) = activity?.baseContext?.getString(resId) ?: ""
