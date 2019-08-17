package com.github.kiolk.alphabet.utils

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Point
import android.net.Uri
import android.support.annotation.StringRes
import android.support.customtabs.CustomTabsIntent
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import com.bluelinelabs.conductor.Controller
import com.github.kiolk.alphabet.R
import saschpe.android.customtabs.CustomTabsHelper
import saschpe.android.customtabs.WebViewFallback
import timber.log.Timber
import java.io.NotActiveException
import java.lang.StringBuilder
import java.util.*
import java.util.regex.Pattern

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.toDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

fun <T> MutableList<T>.randomize(): MutableList<T> {
    val tmp = mutableListOf<T>()
    while (tmp.size != this.size) {
        val item = this.get(Random().nextInt(size))
        if (!tmp.contains(item)) tmp.add(item)
    }
    return tmp
}

fun Controller.getString(@StringRes resId: Int) = activity?.baseContext?.getString(resId) ?: ""

fun Controller.getString(@StringRes resId: Int, vararg vararg: Any) = activity?.baseContext?.resources?.getString(resId, vararg)
        ?: ""

fun Context.getWindowSize(): Point {
    val manager = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    val size = Point()
    manager.getSize(size)
    return size
}

fun Context.getWindowWidth(): Int = getWindowSize().x

fun Context.getWindowHeight(): Int = getWindowSize().y

fun Controller.getContext() = activity as Context

fun Controller.openUrl(url: String) = getContext().openUrl(url)

fun Context.openUrl(url: String) {
    var tempUrl = url
    if (!url.contains(Pattern.compile("^http[s]?://").toRegex())) {
        tempUrl = "http://$url"
    }

    try {
        val intent = CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setToolbarColor(ContextCompat.getColor(this, R.color.general_dark_gray))
                .setShowTitle(true)
                .setCloseButtonIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_add))
                .build()

        CustomTabsHelper.addKeepAliveExtra(this, intent.intent)
        CustomTabsHelper.openCustomTab(this, intent, Uri.parse(tempUrl), WebViewFallback())
    } catch (ex: NotActiveException) {
        Timber.e(ex)
    }
}

fun Controller.sendEmail(email: String){
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "plain/text"
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    intent.putExtra(Intent.EXTRA_SUBJECT, "")
    intent.putExtra(Intent.EXTRA_TEXT, "")

    activity?.startActivity(Intent.createChooser(intent, "Даслаць ліст"))
}

fun List<String>.toContentString(): String{
    val stringBundleBuilder = StringBuilder()
    forEach { if(it.isNotEmpty() && it.isNotBlank()) stringBundleBuilder.append(it).append(", ") }
    return stringBundleBuilder.substring(0 , stringBundleBuilder.length - 2).toString()
}
