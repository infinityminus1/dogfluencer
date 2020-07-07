package com.example.dogfluencer

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.viewpager.widget.ViewPager
import com.example.dogfluencer.ui.main.SectionsPagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private fun generateEventCreationButtons() {
        // Linear Layout inside the HorizontalScrollView will be button parent.
        val parentButtonHolder = findViewById<LinearLayout>(R.id.hsv_layout)
        val textLayout = CoordinatorLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
            marginEnd = 8
            marginStart = 8
        }

        // Loop through all the different events and create event capture buttons
        for (eventType in eventTypes) {
            Log.e("MainActivity", "Creating fab for $eventType")

            val (eventName, eventEmoji) = eventType
            val text = TextView(this)

            // text params
            textLayout.anchorGravity = Gravity.CENTER
            text.layoutParams = textLayout
            text.text = String(Character.toChars(eventEmoji))
            text.elevation = 65F // to be seen above the fab
            text.textSize = 44F
            text.alpha = 1F
            text.setOnClickListener { view ->
                Snackbar.make(view, "Adding $eventName!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()

                // TODO: description should come from user input
                addEvent(eventEmoji.toString(), null)
            }

            parentButtonHolder.addView(text)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // Create fabs here
        generateEventCreationButtons()

        // Create holders for our timestamps here
        generateSchedules()
    }
}