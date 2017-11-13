package com.dvoiss.geocities.interceptors

import android.support.annotation.DrawableRes
import android.support.v7.widget.ContentFrameLayout
import android.view.ViewGroup
import com.dvoiss.geocities.GeocitiesUtils
import com.dvoiss.geocities.R.drawable
import io.github.inflationx.viewpump.InflateResult
import io.github.inflationx.viewpump.Interceptor

/**
 * Look for the [ContentFrameLayout] and intercept it to set a background resource.
 */
class ContentFrameLayoutInterceptor : Interceptor {
  private val drawableResource: Int
    @DrawableRes
    get() = if (Math.random() < 0.50) {
      drawable.background_starry_tiled
    } else {
      drawable.background_clouds_tiled
    }

  override fun intercept(chain: Interceptor.Chain): InflateResult {
    val result = chain.proceed(chain.request())

    if (!result.name().contains("ContentFrameLayout") || result.view() !is ViewGroup) {
      return result
    }

    val rootView = GeocitiesUtils.findParentActivity(result.view()!!.context)
        .window
        .decorView
        .rootView
    rootView.setBackgroundResource(drawableResource)

    return result
  }
}
