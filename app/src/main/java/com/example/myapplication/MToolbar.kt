package com.example.myapplication

import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewAnimationUtils
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.marginStart
import kotlinx.android.synthetic.main.activity_main.*

class MToolbar : Toolbar {



    public lateinit var _title: TextView
    public lateinit var _view: RelativeLayout
    public lateinit var animationReveal: AnimationReveal
    public var _titleAlignment: Int? = 0

    private var viewWidthSize: Int = 0
    private var toolbarWidhtSize: Int = 0
    private var middleToolbar: Int = 0
    private var middleToolbarView: Int = 0
    private var spaceLeftView: Int = 0
    private var spaceRightView: Int = 0

    constructor(context: Context?) : super(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private fun init(context: Context?, attrs: AttributeSet?) {
        val attributes = context?.obtainStyledAttributes(attrs, R.styleable.MToolbar)
        _titleAlignment = attributes?.getInt(R.styleable.MToolbar_mt_title_alignment, 1)
        attributes?.recycle()
    }

    init {
        getTitleTextView()
    }

    fun animateTitle(title: TextView, scroll: ScrollView, duration: Long) {
        animationReveal = AnimationReveal(_title, title, scroll)
        animationReveal.startAnimation()
    }

    override fun getTitle(): CharSequence {
        return getTitleTextView().text
    }

    override fun setTitle(title: CharSequence?) {
        getTitleTextView().apply {
            text = title
        }.requestLayout()
    }

    override fun setTitle(resId: Int) {
        getTitleTextView().apply {
            setText(resId)
        }.requestLayout()
    }

    private fun getTitleTextView(): TextView {

        if (!::_title.isInitialized) {

            _view = RelativeLayout(context).also { v ->

                val lp = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT).also {
                    it.gravity = Gravity.CENTER
                }

                v.layoutParams = lp

                addView(v)
            }

            _title = ToolbarTitleTextView(context).also { tv ->

                tv.setSingleLine()
                tv.ellipsize = TextUtils.TruncateAt.END
                tv.gravity = Gravity.CENTER

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tv.setTextAppearance(R.style.TextAppearance_AppCompat_Widget_ActionBar_Title)
                } else {
                    tv.setTextAppearance(
                        context,
                        R.style.TextAppearance_AppCompat_Widget_ActionBar_Title
                    )
                }

                val lp = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
//                    .also {
//                    it.gravity = Gravity.CENTER
//                }

                tv.layoutParams = lp



                _view.addView(tv)
            }
        }

        return _title
    }

    inner class ToolbarTitleTextView(context: Context?) : TextView(context) {

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)

            if(_titleAlignment == 1) {
                this.textAlignment = TEXT_ALIGNMENT_CENTER
                centralizeToolbarTitle(heightMeasureSpec)
            } else if(_titleAlignment == 2) {
                this.textAlignment = TEXT_ALIGNMENT_VIEW_START
            }

        }

        private fun centralizeToolbarTitle(heightMeasureSpec: Int) {
            toolbarWidhtSize = this@MToolbar.measuredWidth
            viewWidthSize = this@MToolbar._view.measuredWidth
            middleToolbar = toolbarWidhtSize / 2
            middleToolbarView = viewWidthSize / 2

            val array = intArrayOf(0, 0)
            this@MToolbar._view.getLocationInWindow(array)

            spaceLeftView = middleToolbar - array[0]
            spaceRightView = viewWidthSize - spaceLeftView

            val lp = (this.layoutParams as RelativeLayout.LayoutParams).apply  {
                if(spaceLeftView > spaceRightView) {
                    setMeasuredDimension(spaceRightView * 2, MeasureSpec.getSize(heightMeasureSpec))
                    setMargins(middleToolbar - (this@ToolbarTitleTextView.measuredWidth / 2) - array[0], 0, 0, 0)
                } else {
                    setMeasuredDimension(spaceLeftView * 2, MeasureSpec.getSize(heightMeasureSpec))
                    setMargins(0, 0, middleToolbar - (this@ToolbarTitleTextView.measuredWidth / 2) - array[0], 0)
                }
            }

            layoutParams = lp
        }

    }

}