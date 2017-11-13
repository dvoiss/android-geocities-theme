package com.dvoiss.geocities.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.dvoiss.geocities.R

/**
 * This custom toolbar shows several Geocity style elements such as an "under construction" banner,
 * an animated back button, and a hit/visitor counter ([VisitorCounterView]).
 */
class Toolbar : Toolbar {
  private var navigationView: View? = null
  private var titleTextView: TextView? = null

  private val drawableResource: Int
    @DrawableRes
    get() = if (Math.random() < 0.59) {
      R.drawable.background_starry_tiled
    } else {
      R.drawable.background_clouds_tiled
    }

  constructor(context: Context) : super(context) {
    init()
  }

  constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    init()
  }

  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr) {
    init()
  }

  private fun init() {
    val mainView = LayoutInflater.from(context).inflate(R.layout.geo_toolbar, this)
    mainView.findViewById<View>(R.id.root).setBackgroundResource(drawableResource)
    navigationView = mainView.findViewById(R.id.back_button)
    titleTextView = mainView.findViewById<View>(R.id.title) as TextView
    setContentInsetsRelative(0, 0)
  }

  override fun setTitle(resId: Int) {
    titleTextView!!.setText(resId)
  }

  override fun setTitle(title: CharSequence) {
    titleTextView!!.text = title
  }

  override fun setNavigationOnClickListener(listener: View.OnClickListener) {
    navigationView!!.setOnClickListener(listener)
  }

  override fun setOverflowIcon(icon: Drawable?) {
    // no-op, do nothing
  }

  override fun setNavigationIcon(resId: Int) {
    // no-op, use the back-button in the layout above
  }

  override fun setNavigationIcon(icon: Drawable?) {
    // no-op, use the back-button in the layout above
  }
}
