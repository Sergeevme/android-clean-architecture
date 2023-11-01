package com.sergeevme.cleanandroid.presentation.utils

import android.view.MotionEvent
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator

private const val ANIM_DURATION = 200
private const val ANIM_DURATION_BACK = 500
private const val SCALE = .97f
private const val INTERPOLATOR_TENSION = 3.1f

class TouchResize : IViewTouchListener {

    // The change flings forward and overshoots the last value then comes back
    private val overshoot: Interpolator = OvershootInterpolator(INTERPOLATOR_TENSION)

    // Custom view.setOnTouchListener() to animate view when pressed (resize)
    override fun onTouchListener(view: View?) {
        if (view == null) return

        val touchListener = View.OnTouchListener { v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.animate().setInterpolator(overshoot)
                    .scaleX(SCALE)
                    .scaleY(SCALE)
                    .setStartDelay(0)
                    .setDuration(ANIM_DURATION.toLong())
                    .setListener(null)

                return@OnTouchListener false
            }

            if (event.action == MotionEvent.ACTION_UP || event.action ==
                MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_OUTSIDE) {

                // Perform onClickListener
                if (event.action == MotionEvent.ACTION_BUTTON_RELEASE && event.action != MotionEvent.ACTION_OUTSIDE) {
                    v.performClick()
                }

                // Return previous animation state
                v.animate().setInterpolator(overshoot)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setStartDelay(0)
                    .setDuration(ANIM_DURATION_BACK.toLong())
                    .setListener(null)
            }

            return@OnTouchListener false
        }

        view.setOnTouchListener(touchListener)
    }

}