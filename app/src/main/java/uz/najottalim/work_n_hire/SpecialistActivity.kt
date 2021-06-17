package uz.najottalim.work_n_hire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import uz.najottalim.work_n_hire.databinding.ActivitySpecialistActivtyBinding

class SpecialistActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpecialistActivtyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySpecialistActivtyBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.myNavHostFragmentSpecialist)
        binding.bottomNavigationView.setupWithNavController(navController)
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}