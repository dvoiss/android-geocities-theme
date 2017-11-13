package com.dvoiss.geocities.ui

import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.widget.TextView
import com.dvoiss.geocities.R

class ToolbarTextView : TextView {
  constructor(context: Context) : super(context)
  constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
  constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)

  val colors = listOf(
      R.color.red, R.color.orange, R.color.yellow, R.color.green,
      R.color.teal, R.color.blue, R.color.purple
  )

  override fun setText(text: CharSequence?, type: BufferType?) {
    val sb = SpannableStringBuilder(text)

    text?.forEachIndexed { index, c ->
      val fcs = ForegroundColorSpan(resources.getColor(colors[index % colors.size]))
      sb.setSpan(fcs, index, index + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }

    super.setText(sb, type)
  }
}
