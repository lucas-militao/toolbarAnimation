package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.widget.ScrollView
import android.widget.TextView

class AnimationReveal(
    private var view: View,
    private var title: View,
    private var scrollView: ScrollView,
    val duration: Long = 300L
) {

    private var cX: Int = 0
    private var cY: Int = 0
    private var circularReveal: Animator? = null

    init {
        this.cX = (view.right + view.left) / 2
        this.cY = (view.top + view.bottom) / 2
    }

    private fun revealView() {
        view.visibility = View.INVISIBLE

        circularReveal?.cancel()

        circularReveal = ViewAnimationUtils.createCircularReveal(
            view, cX, cY, 0f, view.width.toFloat()
        )

        view.visibility = View.VISIBLE
        circularReveal?.duration = duration
        circularReveal?.start()
    }

    private fun hideView() {

        view.visibility = View.VISIBLE

        circularReveal?.cancel()

        circularReveal = ViewAnimationUtils.createCircularReveal(
            view, cX, cY, view.width.toFloat(), 0f
        )

        print("to aqui oh")

        circularReveal?.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })

        circularReveal?.duration = duration
        circularReveal?.start()
    }

    public fun startAnimation() {

        view.visibility = View.INVISIBLE

        scrollView.viewTreeObserver.addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {

            var positionView = title.y + title.measuredHeight

            val array = intArrayOf(0, 0)
            var position = view.getLocationInWindow(array)

            cX = 0
            cY = array[1] + (view.measuredHeight / 2) - (view as TextView).textSize.toInt()

            if (scrollView.scrollY >= positionView.times(0.8)) {
                if (view.visibility == View.INVISIBLE) {
                    revealView()
                }
            } else {
                if (view.visibility == View.VISIBLE &&
                    (circularReveal?.isStarted == false || circularReveal?.isRunning == false)
                ) {
                    hideView()
                }
            }

        })

    }
}