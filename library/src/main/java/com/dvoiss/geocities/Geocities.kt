@file:JvmName("Geocities")

package com.dvoiss.geocities

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.dvoiss.geocities.GeocitiesUtils.MediaPlayerActivityLifecycleCallbacks
import com.dvoiss.geocities.interceptors.ContentFrameLayoutInterceptor
import com.dvoiss.geocities.interceptors.ImageViewInterceptor
import com.dvoiss.geocities.interceptors.TextViewInterceptor
import com.dvoiss.geocities.interceptors.ToolbarInterceptor
import io.github.inflationx.viewpump.ViewPump
import io.github.inflationx.viewpump.ViewPumpContextWrapper

object Geocities {
  lateinit private var mediaPlayerActivityLifecycleCallbacks: MediaPlayerActivityLifecycleCallbacks

  @JvmStatic
  fun init(application: Application) {
    ViewPump.init(ViewPump.builder()
        .addInterceptor(ImageViewInterceptor())
        .addInterceptor(ToolbarInterceptor())
        .addInterceptor(TextViewInterceptor())
        .addInterceptor(ContentFrameLayoutInterceptor())
        .build())

    mediaPlayerActivityLifecycleCallbacks = MediaPlayerActivityLifecycleCallbacks()
    application.registerActivityLifecycleCallbacks(mediaPlayerActivityLifecycleCallbacks)
  }

  @JvmStatic
  fun wrap(context: Context): ContextWrapper {
    mediaPlayerActivityLifecycleCallbacks.play(context)
    return ViewPumpContextWrapper.wrap(context)
  }
}
