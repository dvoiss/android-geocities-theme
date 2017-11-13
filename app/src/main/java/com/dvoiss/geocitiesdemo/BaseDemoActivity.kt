package com.dvoiss.geocitiesdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.kimo.lib.faker.Faker
import io.kimo.lib.faker.component.number.ColorComponent

open class BaseDemoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_demo)
    setupToolbar()
    setupRecyclerView()
  }

  private fun setupToolbar() {
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    setSupportActionBar(toolbar)
    toolbar.setTitle(R.string.app_name)
    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
  }

  private fun setupRecyclerView() {
    findViewById<RecyclerView>(R.id.recyclerview).also {
      it.layoutManager = LinearLayoutManager(this)
      it.adapter = object : RecyclerView.Adapter<DemoViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup,
            viewType: Int): DemoViewHolder = DemoViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.demo_adapter_item, parent, false))

        override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
          val context = holder.itemView.context
          val faker = Faker.with(context)
          holder.name.text = Faker.Name.fullName()
          holder.contact.text = Faker.Address.randomText()
          holder.details.text = Faker.Lorem.sentence()
          if (shouldShowImageViewColor()) {
            faker.fillWithColor(holder.avatar, ColorComponent(context))
          }
        }

        override fun getItemCount(): Int = 20
      }
    }
  }

  /**
   * I don't want the colors showing on the image-view in the geocities activity,
   * this is good enough for now.
   */
  private fun shouldShowImageViewColor(): Boolean = javaClass == BaseDemoActivity::class.java

  override fun onBackPressed() {
    super.onBackPressed()
    finish()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
    android.R.id.home -> {
      finish()
      true
    }
    else -> super.onOptionsItemSelected(item)
  }

  private class DemoViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(
      itemView) {
    internal var avatar: ImageView = itemView.findViewById(R.id.avatar)
    internal var name: TextView = itemView.findViewById(R.id.name)
    internal var contact: TextView = itemView.findViewById(R.id.contact)
    internal var details: TextView = itemView.findViewById(R.id.details)
  }
}
