package uz.najottalim.work_n_hire.network.paging.samples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.paging.*
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.Flow

class MainActivityExamplePaging : AppCompatActivity() {
//    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

//        for (i in 1 until 20) {
//            HROperations().addVacancy(
//                HRVacancy(
//                    vacancy = Vacancy(
//                        null,
//                        "some_hr_uid_programmatically4",
//                        CompanyInfo(
//                            "Epam",
//                            "epam@systems.com",
//                            "+998977777777",
//                            "https://epam.jpg"
//                        ),
//                        "Python backend developer",
//                        mutableListOf("Django/Flask", "RESTApi", "Clen"),
//                        600,
//                        5000,
//                        "5+ years of experience",
//                        Timestamp(Date(System.currentTimeMillis()))
//                    )
//                )
//            )
//        }

//        HROperations().initialSendMessage(
//            "some_uid",
//            "0W3JpCNCarPcSEV5bZld",
//            "Some message programmatically from hr"
//        )

        /**
         *  setting up paging adapter
         */
//        val viewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.NewInstanceFactory()
//        ).get(MainViewModel::class.java)
//
//        val adapter = VacanciesAdapter()
//        binding.recycler.adapter = adapter
//        CoroutineScope(Dispatchers.Default).launch {
//            viewModel.vacancies.collect {
//                adapter.submitData(it)
//            }
//
//        }


//        val db = Firebase.firestore
//
//        var query: Query = db.collection("test").limit(2)
//        val docs = mutableListOf<DocumentSnapshot>()
//        query.get().addOnSuccessListener {
//            for (document in it.documents) {
//                docs.add(document)
//
//                binding.root.addView(TextView(this).also { textView ->
//                    textView.text = document.id
//                })
//
//            }
//        }
//
//        binding.btnNext.setOnClickListener {
//            query = query.startAfter(docs.last()).limit(2)
//
//            query.get().addOnSuccessListener {
//                for (document in it.documents) {
//                    docs.add(document)
//                    binding.root.addView(TextView(this).also { textView ->
//                        textView.text = document.id
//                    })
//
//                }
//            }
//        }
//
    }

}