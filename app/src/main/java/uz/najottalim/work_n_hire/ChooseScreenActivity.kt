package uz.najottalim.work_n_hire

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ChooseScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_screen)

        val sharedPreferences =
            getSharedPreferences(getString(R.string.preferences_name), Context.MODE_PRIVATE)

        val hr_btn = findViewById<Button>(R.id.hr_button)
        val worker_btn = findViewById<Button>(R.id.worker_btn)


        if (sharedPreferences.getBoolean(getString(R.string.key_is_hr),false)){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        hr_btn.setOnClickListener {

            val editor = sharedPreferences.edit()
            editor.putBoolean(getString(R.string.key_is_hr), true)
            editor.apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        worker_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}