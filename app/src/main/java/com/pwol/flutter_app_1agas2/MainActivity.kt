package com.pwol.flutter_app_1agas2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.pwol.flutter_app_1agas2.data.LoginDataSource
import com.pwol.flutter_app_1agas2.database.services.Service
import com.pwol.flutter_app_1agas2.database.services.FaqsViewModel
import com.pwol.flutter_app_1agas2.database.services.ServicesViewModel
import com.pwol.flutter_app_1agas2.databinding.ActivityMainBinding
import com.pwol.flutter_app_1agas2.protocols.NavigationInterface
import com.pwol.flutter_app_1agas2.ui.login.LoginActivity
import com.pwol.flutter_app_1agas2.ui.settings.SettingsActivity


class MainActivity : AppCompatActivity()  {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private  var activityListener: NavigationInterface? = null
    private var navController : NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //Thread.sleep(2000)

        setTheme(R.style.Theme_1AGas_NoActionBar)

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        val servicesViewModel = ViewModelProvider(this).get(ServicesViewModel::class.java)
        binding.appBarMain.fab.setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show()
            addService(servicesViewModel)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_logout), drawerLayout)
        setupActionBarWithNavController(navController!!, appBarConfiguration)
        navView.setupWithNavController(navController!!)
        val loginRepository = LoginDataSource()
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    //write your implementation here
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    navController?.navigate(R.id.nav_home)
                    true
                }
                R.id.nav_slideshow -> {
                    //write your implementation here
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    navController?.navigate(R.id.nav_slideshow)
                    true
                }

                R.id.nav_logout -> {
                    //write your implementation here
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    loginRepository.logout(context = baseContext)
                    val intentLogin = Intent(this, LoginActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, "message")
                    }
                    startActivity(intentLogin)
                    finish()

                    true
                }
                R.id.nav_gallery -> {
                    //write your implementation here
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    navController?.navigate(R.id.nav_gallery)
                    true
                }
                R.id.nav_settings -> {
                    //write your implementation here
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    //navController.navigate(R.id.nav_settings)
                    val intentActivity = Intent(this, SettingsActivity::class.java).apply {
                        putExtra(AlarmClock.EXTRA_MESSAGE, "message")
                    }
                    //startActivity( intentActivity)
                    myStartActivityForResult<SettingsActivity>(1)


                    true
                }

                else -> {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
            }

        }




    }

    private fun addService(servicesViewModel: ServicesViewModel) {
        servicesViewModel.saveService(
            Service("1234213412412",
                "NOMBRE",
                "APELLIDO",
                "Carrera 79A 334-2",
                "COMERCIAL",
                "PERIODICA",
                false,
                0,
                "",
                "",
                "",
                0,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                2,
                "GRUPO DEMO",
                false,
                "",
                "",
                "",
                "Pendiente",
                "",
                "",
                "BOGOTÁ, D.C.",
                "BOGOTÁ, D.C.",
                "",
                ""
            ))
    }

    inline fun <reified T: Activity> Activity.myStartActivityForResult(requestCode: Int) {
        val intent = Intent(this, T::class.java)
        startActivityForResult(intent, requestCode)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            val intentActivity = Intent(this, SettingsActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "message")
            }
            //startActivity( intentActivity)
            myStartActivityForResult<SettingsActivity>(1)
            true
        }


        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            if (requestCode == 1) {
                val message = data.getStringExtra("refresh")
                if (message == "refresh") {
                    //call function addObserver() in HomeFragment
                    val intent = Intent("refresh_database")
                    intent.putExtra("refresh_database", "refresh_database")
                    // put your all data using put extra
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)



                }
                Toast.makeText(this, "Base de datos eliminada Cierre y Reinicie la aplicación.", Toast.LENGTH_LONG).show()
            }
        }
    }





}