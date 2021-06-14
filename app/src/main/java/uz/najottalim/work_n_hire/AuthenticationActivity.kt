package uz.najottalim.work_n_hire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)


        if(savedInstanceState == null) { // initial transaction should be wrapped like this
            val action = LoginFragmentDirections.actionGlobalLoginFragment()
            showFragment(action)
        }


    }

    fun showFragment(action: NavDirections){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment1) as NavHostFragment
        val navController = navHostFragment.navController
        navController.navigate(action)
    }

}