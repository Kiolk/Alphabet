package com.github.kiolk.alphabet.utils

import android.os.Bundle
import android.os.Parcelable

class BundleBuilder(private val bundle : Bundle) {

    fun getAll(args : Bundle) : BundleBuilder{
        bundle.putAll(args)
        return this
    }

    fun setParseleable(key : String, value : Parcelable?) : BundleBuilder{
        bundle.putParcelable(key, value)
        return this
    }

    fun build() : Bundle{
        return bundle
    }

    fun setParseleableArray(key: String, array: Array<Parcelable>): BundleBuilder{
        bundle.putParcelableArray(key, array)
        return this
    }
}