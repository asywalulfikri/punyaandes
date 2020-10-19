package test.uts.hotel

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_main.*
import test.uts.hotel.fragment.HomeFragment
import test.uts.hotel.fragment.ProfileFragment
import test.uts.hotel.helper.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    internal var prevMenuItem:MenuItem? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewPager.offscreenPageLimit = 2

        bottom_navigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_menu -> {
                    //coordinator.visibility =View.VISIBLE
                    rl_toolbar.visibility = View.VISIBLE
                   // rl_searchView.visibility = View.VISIBLE
                    viewPager.currentItem = 0
                }

                R.id.basket_menu -> {
                   // coordinator.visibility =View.GONE
                    rl_toolbar.visibility = View.VISIBLE
                   // rl_searchView.visibility = View.GONE
                    viewPager.currentItem = 1
                }
            }
            false
        }

        // viewPager.disableScroll(true)
        viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position:Int, positionOffset:Float, positionOffsetPixels:Int) {

            }

            override fun onPageSelected(position:Int) {
                if (prevMenuItem != null)
                {
                    prevMenuItem!!.isChecked = false
                }
                else
                {
                    bottom_navigation.menu.getItem(0).isChecked = false
                }
                Log.d("page", "onPageSelected: $position")
                bottom_navigation.menu.getItem(position).isChecked = true
                prevMenuItem = bottom_navigation.menu.getItem(position)

            }

            override fun onPageScrollStateChanged(state:Int) {

            }
        })



        setupViewPager(viewPager)


    }



    private fun setupViewPager(viewPager:ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()
        adapter.addFragment(homeFragment)
        adapter.addFragment(profileFragment)
        viewPager.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
    }

}
