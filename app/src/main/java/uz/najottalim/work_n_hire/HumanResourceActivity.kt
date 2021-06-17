package uz.najottalim.work_n_hire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import uz.najottalim.work_n_hire.databinding.ActivityHumanResourceBinding

class HumanResourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHumanResourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHumanResourceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupNavigation()


    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.myNavHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.hrFragmentHome,
                R.id.hrFragmentFavorite,
                R.id.hrVacancyFragment,
                R.id.hrChatFragment,
                R.id.hrSettingsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}