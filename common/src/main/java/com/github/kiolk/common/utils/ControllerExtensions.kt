package com.github.kiolk.common.utils

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.bluelinelabs.conductor.Controller

fun Controller.getContext() = activity as Context //TODO extract from app module

fun Controller.fragmentManager(): FragmentManager =
    (getContext() as FragmentActivity).supportFragmentManager