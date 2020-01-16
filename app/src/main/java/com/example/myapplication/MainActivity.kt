package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = ""

        setupView()

    }

    private fun setupView() {

        mScroll.viewTreeObserver.addOnScrollChangedListener(ViewTreeObserver.OnScrollChangedListener {

            if(isViewVisibleInScroll(titulo)) {
                if(toolbar.title == "") {
                    toolbar.title = titulo.text
// TOOLBAR
//                    val array = intArrayOf(0, 0)
//                    toolbar.get_View().getLocationInWindow(array)
//
//                    var inicio = array[0]
//                    var fim = toolbar.get_View().measuredWidth + inicio
//
//                    val circularReveal = ViewAnimationUtils.createCircularReveal(
//                        toolbar,
//                        inicio,
//                        fim,
//                        inicio.toFloat(),
//                        fim.toFloat()
//                    )

                    val circularReveal = ViewAnimationUtils.createCircularReveal(
                        toolbar.get_Title(),
                        (toolbar.get_Title().right + toolbar.get_Title().left) / 2,
                        (toolbar.get_Title().top + toolbar.get_Title().bottom) / 2,
                        0f, toolbar.get_Title().width.toFloat()
                    )

                    circularReveal.duration = 300
                    circularReveal.start()
                }
            } else {
                toolbar.title = ""
            }


//            if(scrollY >= titulo.y + titulo.measuredHeight.times(0.8)) {
//                if (toolbar.title == "") {
//                    toolbar.title = titulo.text
//                    val circularReveal = ViewAnimationUtils.createCircularReveal(
//                        toolbar,
//                        (toolbar.right + toolbar.left) / 2,
//                    (toolbar.top + toolbar.bottom) / 2,
//                    0f, toolbar.width.toFloat()
//                    )
//                    circularReveal.duration = 300
//                    circularReveal.start()
//                }
//            }
//            else
//                toolbar.title = ""


        })

    }

    private fun isViewVisibleInScroll(view : View) : Boolean {

        var scrollY = mScroll.scrollY

        return scrollY >= view.y + view.measuredHeight.times(0.8)

    }
}
