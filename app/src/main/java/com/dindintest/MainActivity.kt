package com.dindintest

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.dindintest.ui.main.SectionsPagerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                val config = view.context.resources.configuration
                val lp = view.layoutParams as ViewGroup.MarginLayoutParams
                when {
                    config.orientation == Configuration.ORIENTATION_PORTRAIT -> {
                        lp.bottomMargin = navBarInsets.bottom
                    }
                    config.layoutDirection == View.LAYOUT_DIRECTION_LTR -> {
                        lp.rightMargin = navBarInsets.right
                    }
                    else -> {
                        lp.leftMargin = navBarInsets.right
                    }
                }
                setOnApplyWindowInsetsListener(null)
                WindowInsetsCompat.CONSUMED
            }
        }

        findViewById<FloatingActionButton>(R.id.fab).apply {
            setOnClickListener { v ->
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
                    .setAction("Action", null)
                    .apply {
                        ViewCompat.setOnApplyWindowInsetsListener(view) { _, _ ->
                            setOnApplyWindowInsetsListener(null)
                            WindowInsetsCompat.CONSUMED
                        }
                    }
                    .show()
            }
        }
    }

}