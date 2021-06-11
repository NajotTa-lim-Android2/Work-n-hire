package uz.najottalim.work_n_hire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class AuthenticationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        if(savedInstanceState == null) { // initial transaction should be wrapped like this
            showFragment(LoginFragment())
        }


    }

    fun showFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fLayout, fragment)
            .commit()
    }

}