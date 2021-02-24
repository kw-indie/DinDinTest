package com.dindintest

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateMarginsRelative
import androidx.viewpager2.widget.ViewPager2
import com.dindintest.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = 0

        val pagerAdapter = SectionsPagerAdapter(this)
        val pager = findViewById<ViewPager2>(R.id.pager).apply {
            adapter = pagerAdapter
        }
        val tabs = findViewById<TabLayout>(R.id.tabs)
        TabLayoutMediator(tabs, pager) { _, _ -> }.attach()

        findViewById<View>(R.id.root_layout).apply {
            ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
                val navBarInsets = insets.getInsets(WindowInsetsCompat.Type.navigationBars())
                val orientation = view.context.resources.configuration.orientation
                val lp = view.layoutParams as ViewGroup.MarginLayoutParams
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    lp.updateMarginsRelative(bottom = navBarInsets.bottom)
                } else {
                    lp.updateMarginsRelative(end = navBarInsets.right)
                }
                WindowInsetsCompat.CONSUMED
            }
        }

        findViewById<FloatingActionButton>(R.id.fab).apply {
            setOnClickListener { v ->
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                    .setAction("Action", null)
                    .show()
            }
        }
    }

}