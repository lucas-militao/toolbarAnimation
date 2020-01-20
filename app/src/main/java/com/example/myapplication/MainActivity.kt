package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var animationReveal : AnimationReveal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()

    }

    private fun setupView() {

        animationReveal = AnimationReveal(toolbar._title, titulo, mScroll)

        animationReveal.startAnimation()

    }

    private fun isViewVisibleInScroll(view : View) : Boolean {

        var scrollY = mScroll.scrollY

        return scrollY >= view.y + view.measuredHeight.times(0.8)

    }
}
