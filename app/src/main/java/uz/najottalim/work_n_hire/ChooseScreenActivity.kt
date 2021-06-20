package uz.najottalim.work_n_hire

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import uz.najottalim.work_n_hire.hr.HumanResourceActivity
import uz.najottalim.work_n_hire.specialist.SpecialistActivity

class ChooseScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_screen)


        val sharedPreferences =
            getSharedPreferences(getString(R.string.preferences_name), Context.MODE_PRIVATE)

        val hr_btn = findViewById<Button>(R.id.hr_button)
        val worker_btn = findViewById<Button>(R.id.worker_btn)


        hr_btn.setOnClickListener {

            val editor = sharedPreferences.edit()
            editor.putBoolean(getString(R.string.key_is_hr), true)
            editor.apply()

            val intent = Intent(this, HumanResourceActivity::class.java)
            startActivity(intent)
            finish()
        }

        worker_btn.setOnClickListener {
            val intent = Intent(this, SpecialistActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}