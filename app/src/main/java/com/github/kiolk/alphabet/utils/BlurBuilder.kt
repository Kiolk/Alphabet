package com.github.kiolk.alphabet.utils

import android.annotation.SuppressLint
import android.content.Context
import android.renderscript.Allocation
import android.renderscript.ScriptIntrinsicBlur
import android.renderscript.RenderScript
import android.graphics.Bitmap
import android.renderscript.Element
import android.graphics.Canvas
import android.view.View


object BlurBuilder {

    private val BITMAP_SCALE = 0.3f
    private val BLUR_RADIUS = 25f

    @SuppressLint("NewApi")
    fun blur(context: Context, view: View): Bitmap {
        val image = getBitmapFromView(view)
        val width = Math.round(image.width * BITMAP_SCALE)
        val height = Math.round(image.height * BITMAP_SCALE)

        val inputBitmap = Bitmap.createScaledBitmap(image, width, height, false)
        val outputBitmap = Bitmap.createBitmap(inputBitmap)

        val rs = RenderScript.create(context)

        val intrinsicBlur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)

        intrinsicBlur.setRadius(BLUR_RADIUS)
        intrinsicBlur.setInput(tmpIn)
        intrinsicBlur.forEach(tmpOut)
        tmpOut.copyTo(outputBitmap)

        return outputBitmap
    }

    fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888)
        val c = Canvas(bitmap)
        view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())
        view.draw(c)
        return bitmap
    }
}