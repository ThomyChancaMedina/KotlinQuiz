package com.kotlinquiz.app.ui.questions.utils

import android.graphics.Color
import androidx.core.content.ContextCompat
import android.view.Window

object Utils {
    // The public static function which can be called from other classes
    fun darkenStatusBar(app: Window, color: Int, dark: Boolean?) {
        if (dark != null) {
            if (dark) {
                app.statusBarColor = darkenColor(ContextCompat.getColor(app.context, color))
            } else {
                app.statusBarColor = normalColor(ContextCompat.getColor(app.context, color))
            }
        } else {
            app.statusBarColor = darkenColor(ContextCompat.getColor(app.context, color))
        }
    }

    // Code to darken the color supplied (mostly color of toolbar)
    private fun darkenColor(color: Int): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= 0.8f
        return Color.HSVToColor(hsv)
    }

    private fun normalColor(color: Int): Int {
        return color
    }



}