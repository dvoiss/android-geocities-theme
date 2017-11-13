package com.dvoiss.geocities.interceptors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import io.github.inflationx.viewpump.InflateResult
import io.github.inflationx.viewpump.Interceptor

/**
 * Intercept a regular [Toolbar] and show our custom Geocities themed toolbar instead.
 */
class ToolbarInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): InflateResult {
    val request = chain.request()
    val view = inflateView(request.name(), request.context(), request.attrs())
    return if (view != null) {
      InflateResult.builder()
          .view(view)
          .name(view.javaClass.name)
          .context(request.context())
          .attrs(request.attrs())
          .build()
    } else {
      chain.proceed(request)
    }
  }

  private fun inflateView(name: String, context: Context,
      attrs: AttributeSet?): View? = if ("android.support.v7.widget.Toolbar" == name) {
    com.dvoiss.geocities.ui.Toolbar(context, attrs!!)
  } else null
}
