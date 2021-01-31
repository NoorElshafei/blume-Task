package com.example.blumetask.util

import android.app.Activity
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import at.wirecube.additiveanimations.additive_animator.AdditiveAnimator
import com.example.blumetask.R

fun Activity.anim_menu(image: ImageView, drawble_active: Int, drawble_dim: Int, visible: Boolean) {
    if (visible) {

        //  direction of rotation
        val displayMetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val x = width / 5
        val y = x / 5
        image.post {

            AdditiveAnimator.animate(this.findViewById<CardView>(R.id.card))
                .setDuration(400)
                .x(image.x + y)
                .y(this.findViewById<CardView>(R.id.card).y)
                .addEndAction {
                    this.findViewById<ImageView>(R.id.scanFram_image)
                        .setImageResource(drawble_active)
                    //  show_icon()
                    image.visibility = View.INVISIBLE
                    Log.e("maaain", "INVISIBLE: ")

                }.then()
                .setDuration(0)
                .rotation(0f)
                .start()
        }
    } else {
        image.visibility = View.VISIBLE
        Log.e("maaain", "VISIBLE: ")

        image.setImageResource(drawble_dim)

    }
}