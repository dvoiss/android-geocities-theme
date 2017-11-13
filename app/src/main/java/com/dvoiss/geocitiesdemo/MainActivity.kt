package com.dvoiss.geocitiesdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setTitle(R.string.app_name)
    findViewById<View>(R.id.launch_normal_button).setOnClickListener {
      startActivity(Intent(this@MainActivity, BaseDemoActivity::class.java))
    }
    findViewById<View>(R.id.launch_geocities_button).setOnClickListener {
      startActivity(Intent(this@MainActivity, GeocitiesDemoActivity::class.java))
    }
  }
}
