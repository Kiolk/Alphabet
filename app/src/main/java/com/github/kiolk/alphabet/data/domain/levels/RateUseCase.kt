package com.github.kiolk.alphabet.data.domain.levels

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.github.kiolk.alphabet.data.domain.UseCase
import javax.inject.Inject

class RateUseCase
@Inject
constructor(private val context: Context) : UseCase<Unit, RateUseCase.Params> {

    override fun execute(params: RateUseCase.Params) {
        val appPackage = "com.findervest.android"//"com.findervest"// context.packageName
        val uri = Uri.parse("market://details?id=" + appPackage)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            context.applicationContext.startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            context.applicationContext.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + appPackage)))
        }
    }

    class Params
}