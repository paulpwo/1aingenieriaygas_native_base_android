package com.pwol.flutter_app_1agas2.stepper

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.pwol.flutter_app_1agas2.R

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3,
    R.string.tab_text_4,
    R.string.tab_text_5,
    R.string.tab_text_6,
    )

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if(position == 0 ){
            return Step1Fragment.newInstance("wsfs", "fasdf")
        }
        if(position == 1 ){
            return Step2Fragment.newInstance("wsfs", "fasdf")
        }
        if(position == 2 ){
            return Step3Fragment.newInstance("wsfs", "fasdf")
        }
        if(position == 3 ){
            return Step4Fragment.newInstance("wsfs", "fasdf")
        }
        if(position == 4 ){
            return Step5Fragment.newInstance("wsfs", "fasdf")
        }
        if(position == 5 ){
            return Step6Fragment.newInstance("wsfs", "fasdf")
        }

        return PlaceholderFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 6
    }
}