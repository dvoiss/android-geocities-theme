package com.dvoiss.geocities.ui

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import com.robinhood.ticker.TickerUtils
import com.robinhood.ticker.TickerView
import java.util.Random

/**
 * A "hit counter" to count visitors. Built on top of Robinhood's [TickerView].
 */
class VisitorCounterView : TickerView {
  private val updateHandler = Handler()

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
    setCharacterList(TickerUtils.getDefaultNumberList())
    setBackgroundColor(Color.parseColor("#000000"))
    textColor = Color.parseColor("#ffffff")
    textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f,
        resources.displayMetrics)
    animationDuration = 100
    gravity = Gravity.START
    text = getRandomNumber(6)
    updateHandler.postDelayed(createRunnable(), 1000)
  }

  private fun createRunnable(): Runnable = Runnable {
    onUpdate()
    updateHandler.postDelayed(createRunnable(), getRandom(1000, 1000).toLong())
  }

  private fun onUpdate() {
    text = (Integer.parseInt(text) + getRandom(3, 1)).toString()
  }

  private fun getRandomNumber(digits: Int): String {
    val digitsInPowerOf10 = Math.pow(10.0, digits.toDouble()).toInt()
    return Integer.toString(
        RANDOM.nextInt(digitsInPowerOf10) + digitsInPowerOf10 * (RANDOM.nextInt(8) + 1))
  }

  private fun getRandom(n: Int, min: Int): Int = RANDOM.nextInt(n) + min

  companion object {
    private val RANDOM = Random(System.currentTimeMillis())
  }
}
