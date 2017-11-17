package com.dvoiss.geocities.interceptors

import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.UnderlineSpan
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import android.widget.TextView
import com.dvoiss.geocities.R
import io.github.inflationx.viewpump.InflateResult
import io.github.inflationx.viewpump.Interceptor
import pl.droidsonroids.gif.GifTextView

/**
 * This interceptor applies several Geocity effects. If the text-view is a button then the classic
 * web-style blue underlined hyperlink text is used. There is a random chance that we turn the
 * text-view into a [GifTextView] and show drawable left and drawable right images. Finally
 * if neither of the above is applied then a blink or marquee effect is applied. We top it all off
 * with the use of comic sans as the default font.
 */
class TextViewInterceptor : Interceptor {
  private val drawable: Int
    @DrawableRes
    get() {
      val random = Math.random()
      return when {
        random < 0.25 -> R.drawable.hot1
        random < 0.50 -> R.drawable.hot2
        random < 0.75 -> R.drawable.new1
        else -> R.drawable.new2
      }
    }

  override fun intercept(chain: Interceptor.Chain): InflateResult {
    val result = getInflateResult(chain)
    val view = result.view() as? TextView ?: return result

    view.setTextColor(getColor(view.context))
    view.typeface = getComicSans(view.context)

    when (view) {
      is Button -> {
        view.background = null
        view.setTextColor(view.resources.getColor(R.color.blue))
        view.text = createUnderlineText(view.text)
      }
      is GifTextView -> applyImages(view)
      else -> applyEffects(view)
    }

    return result
  }

  private fun getInflateResult(chain: Interceptor.Chain): InflateResult {
    val request = chain.request()
    if ("TextView" == request.name()) {
      if (Math.random() < 0.40) {
        // Show a GIF Text View that will show GIF compound drawables.
        val view = GifTextView(request.context(), request.attrs())
        return InflateResult.builder()
            .view(view)
            .name(view.javaClass.name)
            .context(request.context())
            .attrs(request.attrs())
            .build()
      }
    }

    return chain.proceed(request)
  }

  private fun getComicSans(context: Context): Typeface = Typeface.createFromAsset(context.assets,
      if (Math.random() < 0.33) "fonts/comic_sans_bold.ttf" else "fonts/comic_sans_regular.ttf")

  @ColorInt
  private fun getColor(context: Context): Int {
    val random = Math.random()
    return when {
      random < 0.33 -> context.resources.getColor(R.color.red)
      random < 0.66 -> context.resources.getColor(R.color.blue)
      else -> context.resources.getColor(R.color.yellow)
    }
  }

  private fun createUnderlineText(text: CharSequence): CharSequence {
    val underlineText = SpannableString(text)
    underlineText.setSpan(UnderlineSpan(), 0, text.length, 0)
    return underlineText
  }

  private fun applyImages(view: TextView) {
    val random = Math.random()
    when {
      random < 0.33 -> view.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, 0, 0, 0)
      random < 0.66 -> view.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawable, 0)
      else -> view.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, 0, drawable, 0)
    }
  }

  private fun applyEffects(view: TextView) {
    if (Math.random() < 0.50) {
      createBlinkAnimationAndStart(view)
    } else {
      createMarquee(view)
    }
  }

  private fun createMarquee(view: TextView) {
    val bounds = Rect()
    view.paint.getTextBounds(view.text.toString(), 0, view.text.length, bounds)
    view.width = (bounds.width() * .95f).toInt()
    view.ellipsize = TextUtils.TruncateAt.MARQUEE
    view.isFocusable = true
    view.isFocusableInTouchMode = true
    view.marqueeRepeatLimit = -1
    view.isSelected = true
    view.setSingleLine(true)
  }

  private fun createBlinkAnimationAndStart(view: View) {
    val anim = AlphaAnimation(0.0f, 1.0f)
    anim.duration = 1
    anim.startOffset = 250
    anim.repeatMode = Animation.REVERSE
    anim.repeatCount = Animation.INFINITE
    view.startAnimation(anim)
  }
}
