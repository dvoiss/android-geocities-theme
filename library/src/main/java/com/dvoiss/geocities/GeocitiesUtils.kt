@file:JvmName("GeocitiesUtils")

package com.dvoiss.geocities

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.media.MediaPlayer
import android.os.Bundle
import java.util.*

object GeocitiesUtils {
  fun createMediaPlayer(context: Context): MediaPlayer {
    val songResource = listOf(R.raw.tubthumping, R.raw.africa)[Random().nextInt(2)]
    val mp = MediaPlayer.create(context, songResource)
    mp.isLooping = true
    mp.setOnCompletionListener { it.release() }
    return mp
  }

  /**
   * This method is referenced from https://github.com/pyricau/frenchtoast.
   */
  fun findParentActivity(context: Context): Activity {
    val appContext = context.applicationContext
    var unwrapped: Context? = context
    while (true) {
      if (unwrapped is Activity) {
        return unwrapped
      }

      if (unwrapped === null || unwrapped === appContext || unwrapped !is ContextWrapper ||
          unwrapped.baseContext === unwrapped) {
        throw FindActivityException(context)
      }

      unwrapped = unwrapped.baseContext
    }
  }

  internal class FindActivityException(context: Context) : IllegalArgumentException(
      "Could not find Activity from context: " + context)

  class MediaPlayerActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
    private var mediaPlayer: MediaPlayer? = null

    fun play(context: Context) {
      mediaPlayer = GeocitiesUtils.createMediaPlayer(context)
      mediaPlayer?.start()
    }

    override fun onActivityPaused(activity: Activity) {
      mediaPlayer?.stop()
      mediaPlayer = null
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) {}

    override fun onActivityDestroyed(activity: Activity) {}
  }
}
