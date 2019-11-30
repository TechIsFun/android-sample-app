package com.github.techisfun.cleanarchitecture

import android.content.res.Resources
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.github.techisfun.cleanarchitecture.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_bottom_navigation.*
import kotlinx.android.synthetic.main.activity_bottom_navigation.toolbar
import timber.log.Timber
import javax.inject.Inject

class BottomNavigationActivity : AppCompatActivity(),
        HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        setSupportActionBar(toolbar)

        AndroidInjection.inject(this)
        
        bottomn_nav_view.apply {
            setOnNavigationItemSelectedListener(this@BottomNavigationActivity)
            setupWithNavController(Navigation.findNavController(this@BottomNavigationActivity,
                R.id.nav_host_fragment
            ))
        }

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        navController = host.navController
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                destination.id.toString()
            }
            Timber.v("Navigated to $dest")
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        Timber.v("onNavigationItemSelected(), menuItem.itemId=${menuItem.itemId}")
        return true
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

}
