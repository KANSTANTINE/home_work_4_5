package kg.geeks.taskapp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kg.geeks.taskapp5.data.local.pref.Pref
import kg.geeks.taskapp5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = Pref(this)

        initGeneralUI()
    }

    private fun initGeneralUI() {
        val bottomNavView: BottomNavigationView = binding.bottomNavMenu
        val navController = findNavController(R.id.nav_host_fragment_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_profile,
                R.id.onBoardFragment,
                R.id.taskFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)

        val bottomNavFragments = setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.navigation_profile,
        )

        if (!pref.isBoardingSeen())
            navController.navigate(R.id.onBoardFragment)

        navController.addOnDestinationChangedListener { controller, destionation, arguments ->
            bottomNavView.isVisible = bottomNavFragments.contains(destionation.id)
            if (destionation.id == R.id.onBoardFragment)
                supportActionBar?.hide()
            else supportActionBar?.show()
        }
    }
}