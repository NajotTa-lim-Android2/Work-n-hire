package uz.najottalim.work_n_hire.authentication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import uz.najottalim.work_n_hire.R
import uz.najottalim.work_n_hire.hr.HumanResourceActivity
import uz.najottalim.work_n_hire.specialist.SpecialistActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null){
            val action =
                LoginFragmentDirections.actionGlobalBlankFragment()
            showFragment(action)
        } else {
            val action =
                LoginFragmentDirections.actionGlobalLoginFragment()
            showFragment(action)
        }


        val sharedPreferences =
            getSharedPreferences(getString(R.string.preferences_name), Context.MODE_PRIVATE)

        Log.d("Timber ishlamadi","Shared Preference, ${sharedPreferences.getBoolean(getString(R.string.key_is_hr),false)}")

        if (sharedPreferences.getBoolean(getString(R.string.key_is_hr),false)){
            val intent = Intent(this, HumanResourceActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            val intent = Intent(this, SpecialistActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showFragment(action: NavDirections){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(action)
    }
}