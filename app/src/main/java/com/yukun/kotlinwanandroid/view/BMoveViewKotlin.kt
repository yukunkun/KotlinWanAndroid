package com.yukun.kotlinwanandroid.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.yukun.kotlinwanandroid.R

/**
 * author: kun .
 * date:   On 2018/12/12
 */
class BMoveViewKotlin(context: Context?,attrs: AttributeSet?, defStyleAttr: Int) : View(context,attrs,defStyleAttr) {

    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mPaint: Paint? = null
    private var mPaintLine: Paint? = null
    private var mRectF: RectF? = null
    private var mBoardWidth = 50
    private var firstPos: Int = 0  //第一次点击位置
    private var mRoationx = 0
    private var mRadio = 5
    private var position = 0//点击到的button位置
    private var mLineEndLength: Int = 0
    private var mLineLength: Int = 0
    private var mCircleColor: Int = 0
    private var mLineColor: Int = 0
    private var mLineDuration: Int = 0
    private var mLineWidth: Int = 0
    private var mCircleDuration: Int = 0
    private var mCircleCenterColor: Int = 0
    private var mCirclemRadio: Int = 0
    private var mButonCount: Int = 0

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.BMoveView, defStyleAttr, 0)

        val n = a.indexCount

        for (i in 0 until n) {
            val attr = a.getIndex(i)
            when (attr) {
                R.styleable.BMoveView_circleColor -> mCircleColor = a.getColor(attr, Color.WHITE)
                R.styleable.BMoveView_lineColor -> mLineColor = a.getColor(attr, Color.GRAY)
                R.styleable.BMoveView_circleCenterColor -> mCircleCenterColor = a.getColor(attr, Color.GRAY)
                R.styleable.BMoveView_lineDuration -> mLineDuration = a.getInt(attr, 500)
                R.styleable.BMoveView_lineWidth -> mLineWidth = a.getInt(attr, 5)
                R.styleable.BMoveView_circleDuration -> mCircleDuration = a.getInt(attr, 500)
                R.styleable.BMoveView_circlemRadio -> mCirclemRadio = a.getInt(attr, 500)
                R.styleable.BMoveView_buttonCount -> mButonCount = a.getInt(attr, 3)
            }
        }
        a.recycle()
        mBoardWidth = dip2px(context, mCirclemRadio.toFloat())
        mRadio = dip2px(context, mLineWidth.toFloat())
        mPaint = Paint()
        mPaintLine = Paint()
    }

    /**
     * 初始化第一次的位置
     * @param firstPos
     * @param lastPos
     */
    fun setTwoPos(firstPos: Int, lastPos: Int) {
        this.firstPos = firstPos
        this.position = lastPos
        this.mRoationx = 0
        //动画的方法 （lastPos-firstPos）两次相减得到需要移动的距离
        leftToRigth(lastPos - firstPos)
    }

    /**
     * button个数
     * @param butonCount
     */

    fun setButonCount(butonCount: Int) {
        mButonCount = butonCount
    }

    /**
     *
     * @param startLineLastPosition 正为向右,负为想左,如果是1.则跨度为一,如果是2,则跨度为2;
     */
    private fun leftToRigth(startLineLastPosition: Int) {
        startAnim()
        startLineAnim(startLineLastPosition)
        startLineEndAnim(startLineLastPosition)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //画弧度
        mPaint!!.color = mCircleColor
        mPaint!!.isAntiAlias = true
        mPaint!!.strokeWidth = mRadio.toFloat()
        mPaint!!.style = Paint.Style.STROKE//只有边
        //画圆弧的矩形位置
        mRectF = RectF((mWidth / (mButonCount * 2) - mBoardWidth + position * mWidth / mButonCount).toFloat(), (mHeight / 2 - mBoardWidth).toFloat(), (mWidth / (mButonCount * 2) + mBoardWidth + position * mWidth / mButonCount).toFloat(), (mHeight / 2 + mBoardWidth).toFloat())
        canvas.drawArc(mRectF!!, 90f, mRoationx.toFloat(), false, mPaint!!)
        //画圆覆盖
        mPaintLine!!.color = Color.BLUE
        mPaintLine!!.isAntiAlias = true
        mPaintLine!!.style = Paint.Style.FILL
        //可以画内圆圈的颜色
        canvas.drawArc(mRectF!!, 90f, mRoationx.toFloat(), true, mPaintLine!!)
        //画线条
        mPaintLine!!.color = mLineColor
        mPaintLine!!.strokeWidth = mRadio.toFloat()
        //起始和结束不同,每次动画结束位置是相同的,控制起始点和结束点
        canvas.drawLine((mWidth / (mButonCount * 2) + firstPos * mWidth / mButonCount + mLineEndLength).toFloat(), (mHeight / 2 + mBoardWidth).toFloat(), (mWidth / (mButonCount * 2) + firstPos * mWidth / mButonCount + mLineLength).toFloat(), (mHeight / 2 + mBoardWidth).toFloat(), mPaintLine!!)
    }

    //圆圈的动画
    fun startAnim() {
        val animator = ValueAnimator.ofInt(0, 360)
        animator.duration = mCircleDuration.toLong()
        animator.startDelay = mCircleDuration.toLong()
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation ->
            mRoationx = animation.animatedValue as Int
            postInvalidate()
        }
        animator.start()
    }

    //线条开始的动画
    private fun startLineAnim(startLineLastPosition: Int) {
        val animator = ValueAnimator.ofInt(0, mWidth / mButonCount * startLineLastPosition)
        animator.duration = mLineDuration.toLong()
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation ->
            mLineLength = animation.animatedValue as Int
            postInvalidate()
        }
        animator.start()
    }

    //线条结束的动画
    private fun startLineEndAnim(startLineLastPosition: Int) {
        val animator = ValueAnimator.ofInt(0, mWidth / mButonCount * startLineLastPosition)
        animator.duration = mCircleDuration.toLong()
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation ->
            mLineEndLength = animation.animatedValue as Int
            postInvalidate()
        }
        animator.start()
    }

    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}