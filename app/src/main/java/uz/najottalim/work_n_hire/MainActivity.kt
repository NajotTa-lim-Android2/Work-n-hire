package uz.najottalim.work_n_hire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = FirebaseAuth.getInstance().currentUser

        if (user != null){
            val action = LoginFragmentDirections.actionGlobalBlankFragment()
            showFragment(action)
        } else {

            val action = LoginFragmentDirections.actionGlobalLoginFragment()
            showFragment(action)
        }
    }


    fun showFragment(action: NavDirections){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(action)
    }
}