package com.idw.project.tes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.idw.project.tes.Fragment.BerandaFragment
import com.idw.project.tes.Fragment.ShoppingFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Jakarta, Indonesia"


        adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(BerandaFragment(), "Tour")
        adapter.addFragment(ShoppingFragment(), "Shopping")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }


}
