package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import kotlinx.android.synthetic.main.activity_main.*

class AnimationReveal {

    private lateinit var view: View
    private var cX: Int = 0
    private var cY: Int = 0

    constructor(view: View) {
        this.view = view
        this.cX = (view.right + view.left) / 2
        this.cY = (view.top + view.bottom) / 2
    }

    public fun revealView() {
        val circularReveal = ViewAnimationUtils.createCircularReveal(
            view, cX, cY, 0f, view.width.toFloat()
        )

        circularReveal.start()
    }

    public fun hideView() {
        val circularReveal = ViewAnimationUtils.createCircularReveal(
            view, cX, cY, 0f, view.width.toFloat()
        )

        circularReveal.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })

        circularReveal.start()
    }
}