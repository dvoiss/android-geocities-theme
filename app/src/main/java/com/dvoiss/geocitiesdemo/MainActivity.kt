package com.dvoiss.geocitiesdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.IntegerRes
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setTitle(R.string.app_name)
    startActivityOnClick(R.id.launch_normal_button, BaseDemoActivity::class.java)
    startActivityOnClick(R.id.launch_geocities_button, GeocitiesDemoActivity::class.java)
  }

  private fun startActivityOnClick(@IdRes idRes: Int, activityClass: Class<out Activity>) {
    findViewById<View>(idRes).setOnClickListener {
      startActivity(Intent(this@MainActivity, activityClass))
    }
  }
}
