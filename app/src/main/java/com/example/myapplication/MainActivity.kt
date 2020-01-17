package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()

    }

    private fun setupView() {

        toolbar._title.visibility = View.INVISIBLE
        toolbar._title.text = titulo.text

        mScroll.viewTreeObserver.addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {

            val cX = (toolbar._title.right + toolbar._title.left) / 2
            val cY = (toolbar._title.top + toolbar._title.bottom) / 2

            if(isViewVisibleInScroll(titulo)) {
                if (toolbar._title.visibility == View.INVISIBLE) {
                    val circularReveal = ViewAnimationUtils.createCircularReveal(
                        toolbar._title, cX, cY, 0f, toolbar._title.width.toFloat()
                    )

//                circularReveal.duration = 300
                    toolbar._title.visibility = View.VISIBLE
                    circularReveal.start()
                }
            } else {

                val circularReveal = ViewAnimationUtils.createCircularReveal(
                    toolbar._title, cX, cY, toolbar._title.width.toFloat(), 0f
                )

                circularReveal.addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        toolbar._title.visibility = View.INVISIBLE
                    }

                })

                circularReveal.start()
            }
        })

    }

    private fun isViewVisibleInScroll(view : View) : Boolean {

        var scrollY = mScroll.scrollY

        return scrollY >= view.y + view.measuredHeight.times(0.8)

    }
}
