package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_main.*

class AnimationReveal {

    private lateinit var view: View
    private lateinit var title: View
    private lateinit var scrollView: ScrollView
    private var cX: Int = 0
    private var cY: Int = 0
    private var circularReveal: Animator? = null

    constructor(view: View, title: View, scrollView: ScrollView) {
        this.view = view
        this.title = title
        this.scrollView = scrollView
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
        circularReveal?.duration = 300
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

        circularReveal?.duration = 300
        circularReveal?.start()
    }

    public fun startAnimation() {

        view.visibility = View.INVISIBLE

        scrollView.viewTreeObserver.addOnScrollChangedListener( ViewTreeObserver.OnScrollChangedListener {

            var positionView = title.y + title.measuredHeight

            val array = intArrayOf(0, 0)
            var position = view.getLocationInWindow(array)

            cX = array[0]
            cY = array[1]

            if(scrollView.scrollY >= positionView.times(0.8)) {
                if(view.visibility == View.INVISIBLE) {
                    revealView()
                }
            } else {
                if(view.visibility == View.VISIBLE &&
                    (circularReveal?.isStarted == false || circularReveal?.isRunning == false)) {
                    hideView()
                }
            }

        })

    }
}