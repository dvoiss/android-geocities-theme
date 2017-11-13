package com.dvoiss.geocitiesdemo

import android.app.Application
import com.dvoiss.geocities.Geocities

class GeocitiesApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Geocities.init(this)
  }
}
