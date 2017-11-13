package com.dvoiss.geocities.interceptors

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.dvoiss.geocities.R.drawable
import io.github.inflationx.viewpump.InflateResult
import io.github.inflationx.viewpump.Interceptor
import pl.droidsonroids.gif.GifImageView

/**
 * Intercepts the standard ImageView and replaces it with a [GifImageView] or replaces the
 * image with one of the resources below.
 */
class ImageViewInterceptor : Interceptor {
  private val drawableResource: Int
    @DrawableRes
    get() {
      val random = Math.random()
      return when {
        random < 0.25 -> drawable.image_missing_internet_explorer
        random < 0.50 -> drawable.image_missing_netscape
        random < 0.75 -> drawable.under_construction_triangle
        else -> // We will leave a small chance to use whatever resource was already specified.
          DO_NOT_REPLACE_IMAGE
      }
    }

  private val animatedDrawableResource: Int
    @DrawableRes
    get() {
      val random = Math.random()
      return when {
        random < 0.16 -> drawable.under_construction_animated
        random < 0.33 -> drawable.under_construction_triangle_animated
        random < 0.50 -> drawable.fire
        random < 0.67 -> drawable.heart
        random < 0.83 -> drawable.baby
        else -> drawable.dollar
      }
    }

  override fun intercept(chain: Interceptor.Chain): InflateResult {
    if ("ImageView" != chain.request().name()) {
      return chain.proceed(chain.request())
    }

    val result = getInflateResult(chain)
    val view = result.view() as? ImageView ?: return result
    val drawableResource = if (view is GifImageView) animatedDrawableResource else drawableResource
    if (drawableResource != DO_NOT_REPLACE_IMAGE) {
      view.setImageResource(drawableResource)
    }

    return result
  }

  private fun getInflateResult(chain: Interceptor.Chain): InflateResult {
    val request = chain.request()

    // 50% chance to have an animated GIF Image View.
    if (Math.random() < 0.50) {
      val view = GifImageView(request.context(), request.attrs())
      return InflateResult.builder()
          .view(view)
          .name(view.javaClass.name)
          .context(request.context())
          .attrs(request.attrs())
          .build()
    }

    return chain.proceed(request)
  }

  companion object {
    private val DO_NOT_REPLACE_IMAGE = -1
  }
}
