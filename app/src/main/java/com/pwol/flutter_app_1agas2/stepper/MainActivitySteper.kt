package com.pwol.flutter_app_1agas2.stepper

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.pwol.flutter_app_1agas2.databinding.ActivityMainSteperBinding


class MainActivitySteper : AppCompatActivity() {

    private lateinit var binding: ActivityMainSteperBinding
    private lateinit var btnPrev: com.google.android.material.button.MaterialButton
    private lateinit var btnNext: com.google.android.material.button.MaterialButton
    private lateinit var btnFinish: com.google.android.material.button.MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainSteperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        viewPager.setOnTouchListener { v, event -> true }
        val tabs: TabLayout = binding.tabs
        btnPrev = binding.btnPrev
        btnNext = binding.btnNext
        btnFinish = binding.btnFinish
        btnPrev.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem - 1
        }
        btnNext.setOnClickListener {
            viewPager.currentItem = viewPager.currentItem + 1
        }
        btnFinish.setOnClickListener {
            finish()
        }


        tabs.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener (object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position

                if (viewPager.currentItem == 0) {
                    btnPrev.setVisibility(View.GONE)
                    btnNext.setVisibility(View.VISIBLE)
                    btnFinish.setVisibility(View.GONE)
                } else if (viewPager.currentItem == 1) {
                    btnPrev.setVisibility(View.VISIBLE)
                    btnNext.setVisibility(View.VISIBLE)
                    btnFinish.setVisibility(View.GONE)
                } else if (viewPager.currentItem == 2) {
                    btnPrev.setVisibility(View.VISIBLE)
                    btnNext.setVisibility(View.VISIBLE)
                    btnFinish.setVisibility(View.GONE)
                }else if (viewPager.currentItem == 3) {
                    btnPrev.setVisibility(View.VISIBLE)
                    btnNext.setVisibility(View.VISIBLE)
                    btnFinish.setVisibility(View.GONE)
                }else if (viewPager.currentItem == 4) {
                    btnPrev.setVisibility(View.VISIBLE)
                    btnNext.setVisibility(View.VISIBLE)
                    btnFinish.setVisibility(View.GONE)
                }else if (viewPager.currentItem == 5) {
                    btnPrev.setVisibility(View.VISIBLE)
                    btnNext.setVisibility(View.GONE)
                    btnFinish.setVisibility(View.VISIBLE)
                }

            }
        })
    }
}