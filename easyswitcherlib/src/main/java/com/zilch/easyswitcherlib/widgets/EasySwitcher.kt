package com.zilch.easyswitcherlib.widgets

import android.animation.*
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.zilch.easyswitcherlib.R

class EasySwitcher @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mDefaultWidth: Float
    private var mDefaultHeight: Float
    private var mAnimTime: Int
    private var mCloseBgColor: Int
    private var mOpenBgColor: Int
    private var mSwitcherOpened: Boolean
    private var mCurrBgColor: Int
    private var mSwitcherColor: Int //switcher circle color
    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mSwitcherRadius = 0 //switcher circlr radius
    private var mSwitchPadding = 0 //the distance between the switcher and edge of round rect
    private var mSwitcherCenterPoint = Point() //switcher center point
    private var mAnimXGap = 0 //the anim deltaX
    private var mTranslationVh: PropertyValuesHolder? = null
    private var mArgbVh: PropertyValuesHolder? = null
    private var mTotalAnimator: ObjectAnimator? = null
    private var mBackGroundRectF: RectF = RectF() //the RectF for draw round rect
    private var mStateChangedLis: SwitchStateChangedListener? = null

    init {
        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EasySwitcher)
        mAnimTime = typedArray.getInt(R.styleable.EasySwitcher_sbAnimTime, 500)
        mOpenBgColor = typedArray.getColor(R.styleable.EasySwitcher_sbOpenBgColor, Color.parseColor("#008cff"))
        mCloseBgColor = typedArray.getColor(R.styleable.EasySwitcher_sbCloseBgColor, Color.parseColor("#d9d9d9"))
        mSwitcherColor = typedArray.getColor(R.styleable.EasySwitcher_sbSwitcherColor, Color.WHITE)
        mSwitcherOpened = typedArray.getString(R.styleable.EasySwitcher_sbSwitchStatus)?.toInt() == 1
        mCurrBgColor = if (mSwitcherOpened) mOpenBgColor else mCloseBgColor
        typedArray.recycle()

        mDefaultWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f,
                getContext().resources.displayMetrics)

        mDefaultHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30f,
                getContext().resources.displayMetrics)

        setOnClickListener {
            changeSwitcherStatus()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)

        var finalWidth = widthSpecSize
        var finalHeight = heightSpecSize

        if (widthSpecMode == MeasureSpec.AT_MOST) {
            finalWidth = mDefaultWidth.toInt()
        }

        if (heightSpecMode == MeasureSpec.AT_MOST) {
            finalHeight = mDefaultHeight.toInt()
        }

        if (finalWidth < finalHeight) { //the width must be bigger than height
            finalWidth = finalHeight
        }

        setMeasuredDimension(finalWidth, finalHeight)

        initialize()
    }

    /**
     * initialize the values
     */
    private fun initialize() {
        mSwitchPadding = measuredHeight / 15
        mSwitcherRadius = measuredHeight / 2 - mSwitchPadding

        mAnimXGap = measuredWidth - mSwitchPadding * 2 - mSwitcherRadius * 2

        mSwitcherCenterPoint.x =
                if (mSwitcherOpened)
                    measuredWidth - mSwitchPadding - mSwitcherRadius
                else
                    mSwitchPadding + mSwitcherRadius
        mSwitcherCenterPoint.y = mSwitchPadding + mSwitcherRadius

        mBackGroundRectF.left = 0f
        mBackGroundRectF.top = 0f
        mBackGroundRectF.right = measuredWidth.toFloat()
        mBackGroundRectF.bottom = measuredHeight.toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        //draw background round rect
        mPaint.color = mCurrBgColor
        canvas.drawRoundRect(mBackGroundRectF, measuredHeight.toFloat() / 2, measuredHeight.toFloat() / 2, mPaint)
        //draw switcher
        mPaint.color = mSwitcherColor
        canvas.drawCircle(mSwitcherCenterPoint.x.toFloat(),
                mSwitcherCenterPoint.y.toFloat(),
                mSwitcherRadius.toFloat(), mPaint)
    }

    fun setBgColor(color: Int) {
        mCurrBgColor = color
        invalidate()
    }

    fun getBgColor(): Int {
        return mCurrBgColor
    }

    fun setAnimXGap(xGap: Int) {
        mSwitcherCenterPoint.x = mSwitcherRadius + mSwitchPadding + xGap
    }

    fun getAnimXGap(): Int {
        return mSwitcherCenterPoint.x - mSwitchPadding - mSwitcherRadius
    }

    private fun changeSwitcherStatus() {
        if (mTotalAnimator?.isRunning == true) {
            return
        }
        mArgbVh = null
        mTranslationVh = null
        if (mSwitcherOpened) {
            mArgbVh = PropertyValuesHolder.ofInt("bgColor", mOpenBgColor, mCloseBgColor)
            mArgbVh?.setEvaluator(ArgbEvaluator())
            mTranslationVh = PropertyValuesHolder.ofInt("animXGap", mAnimXGap, 0)
        } else {
            mArgbVh = PropertyValuesHolder.ofInt("bgColor", mCloseBgColor, mOpenBgColor)
            mArgbVh?.setEvaluator(ArgbEvaluator())
            mTranslationVh = PropertyValuesHolder.ofInt("animXGap", 0, mAnimXGap)
        }
        if (mTotalAnimator == null) {
            mTotalAnimator = ObjectAnimator.ofPropertyValuesHolder(this, mArgbVh, mTranslationVh)
                    .setDuration(mAnimTime.toLong())
            mTotalAnimator?.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    mStateChangedLis?.onStateChanged(mSwitcherOpened)
                }
            })
        } else {
            mTotalAnimator?.setValues(mArgbVh, mTranslationVh)
        }
        mTotalAnimator?.start()
        mSwitcherOpened = !mSwitcherOpened
    }

    /**
     * get the current state of switcher
     * @return whether this switcher is open or not
     */
    fun isOpened() = mSwitcherOpened

    /**
     * Register a callback to be invoked when the switcher state changed.
     * @param stateChangedLis the callback that will run.
     */
    fun setOnStateChangedListener(stateChangedLis: SwitchStateChangedListener) {
        this.mStateChangedLis = stateChangedLis
    }


    interface SwitchStateChangedListener {
        /**
         * @param isOpen is switcher in open state or not
         */
        fun onStateChanged(isOpen: Boolean)
    }
}