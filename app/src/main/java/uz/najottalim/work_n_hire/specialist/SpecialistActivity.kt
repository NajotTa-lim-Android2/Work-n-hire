package uz.najottalim.work_n_hire.specialist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import uz.najottalim.work_n_hire.R
import uz.najottalim.work_n_hire.databinding.ActivitySpecialistActivtyBinding

class SpecialistActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpecialistActivtyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpecialistActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()


    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.myNavHostFragmentSpecialist)
        binding.bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.specialistJobsFragment,
                R.id.specialistFavoriteFragment,
                R.id.specialistActivityFragment,
                R.id.specialistChatFragment,
                R.id.specialistSettingsFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}