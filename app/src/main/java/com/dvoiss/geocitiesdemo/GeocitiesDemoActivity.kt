package com.dvoiss.geocitiesdemo

import android.content.Context
import com.dvoiss.geocities.Geocities

class GeocitiesDemoActivity : BaseDemoActivity() {
  override fun attachBaseContext(newBase: Context) {
    super.attachBaseContext(Geocities.wrap(newBase))
  }

  override fun shouldShowImageViewColor(): Boolean = false
}
