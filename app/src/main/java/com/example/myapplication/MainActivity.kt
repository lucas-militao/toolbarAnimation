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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        toolbar.animateTitle(titulo, mScroll, 100)
    }

    private fun setupView() {



    }

    private fun isViewVisibleInScroll(view : View) : Boolean {

        var scrollY = mScroll.scrollY

        return scrollY >= view.y + view.measuredHeight.times(0.8)

    }
}
