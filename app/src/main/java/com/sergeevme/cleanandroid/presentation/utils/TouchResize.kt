package com.sergeevme.cleanandroid.presentation.utils

import android.view.MotionEvent
import android.view.View
import android.view.animation.Interpolator
import android.view.animation.OvershootInterpolator

class TouchResize : IViewTouchListener {

    private var overshoot: Interpolator = OvershootInterpolator(3.1f)
    private var animDuration = 200
    private var animDurationBack = 500
    private var scale = .97f

    override fun onTouchListener(view: View?) {
        if (view == null) return

        val touchListener = View.OnTouchListener { v: View, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.animate().setInterpolator(overshoot)
                    .scaleX(scale)
                    .scaleY(scale)
                    .setStartDelay(0)
                    .setDuration(animDuration.toLong())
                    .setListener(null)

                return@OnTouchListener false
            }

            if (event.action == MotionEvent.ACTION_UP || event.action ==
                MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_OUTSIDE) {

                if (event.action == MotionEvent.ACTION_BUTTON_RELEASE && event.action != MotionEvent.ACTION_OUTSIDE) {
                    v.performClick()
                }

                v.animate().setInterpolator(overshoot)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setStartDelay(0)
                    .setDuration(animDurationBack.toLong())
                    .setListener(null)
            }

            return@OnTouchListener false
        }

        view.setOnTouchListener(touchListener)
    }

}