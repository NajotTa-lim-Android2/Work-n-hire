    package uz.najottalim.work_n_hire.hr

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import uz.najottalim.work_n_hire.R
import uz.najottalim.work_n_hire.Vacancy
import uz.najottalim.work_n_hire.databinding.FragmentHrVacancyBinding
import uz.najottalim.work_n_hire.databinding.FragmentRegisterBinding
import uz.najottalim.work_n_hire.hr.models.CompanyInfo
import java.util.*


    private const val PARAM_uid = "uid"
    private const val PARAM_hr_uid = "hr_uid"

    class HrVacancyFragment : Fragment() {

    private var uid: String? = null
    private var hr_uid: String? = null

    private var _binding: FragmentHrVacancyBinding? = null
    private val binding get() = _binding!!

    lateinit var company_info: CompanyInfo
    private var job_title: String = ""

    lateinit var required_skills: MutableList<String>

    private var salaryFrom: Int = 0
    private var salaryTo: Int = 0

    private var addition: String = ""

    private var publication_time: Long = 0L

    private val docRef: DocumentReference = FirebaseFirestore.getInstance()
        .document("sampleData/inspiration")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uid = it.getString(PARAM_uid)
            hr_uid = it.getString(PARAM_hr_uid)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHrVacancyBinding.inflate(layoutInflater, container, false)

        binding.addSkillBtn.setOnClickListener {
            when{
                TextUtils.isEmpty(binding.edtSkill.text.toString().trim(){it <= ' '}) ->{
                    showSnackbar("Skill")
                } else -> {
                    val currentSkill = binding.edtSkill.text.toString()
                    required_skills.add(currentSkill)

                    val allSkills = binding.txtSkillsDisplay.text.toString() + currentSkill
                    binding.txtSkillsDisplay.text = allSkills

                }
            }
        }
        binding.btnPublish.setOnClickListener {

            when{
                TextUtils.isEmpty(binding.edtJobTitle.text.toString().trim(){it <= ' '}) -> {
                    showSnackbar("Job Title")
                }
                TextUtils.isEmpty(binding.edtCompanyEmail.text.toString().trim(){it <= ' '}) -> {
                    showSnackbar("Email")
                }
                TextUtils.isEmpty(binding.edtCompanyNumber.text.toString().trim(){it <= ' '}) -> {
                    showSnackbar("Phone Number")
                }
                TextUtils.isEmpty(binding.edtCompanyName.text.toString().trim(){it <= ' '}) -> {
                    showSnackbar("Company Name")
                }

                else ->{
                    job_title = binding.edtJobTitle.text.toString()
                    company_info = CompanyInfo(binding.edtCompanyName.text.toString(),
                    binding.edtCompanyEmail.text.toString(),
                    binding.edtCompanyNumber.text.toString(),
                    "")

                    salaryFrom = binding.edtSalaryFrom.text.toString().toInt()
                    salaryTo = binding.edtSalaryTo.text.toString().toInt()
                    addition = binding.edtAddition.text.toString()
                    publication_time = Calendar.getInstance().timeInMillis

                    val vacancy: Vacancy = Vacancy(uid!!,
                        hr_uid!!,
                        company_info,
                        job_title,
                        required_skills,
                        salaryFrom,
                        salaryTo,
                        addition,
                        publication_time
                    )

                    docRef.set(vacancy)
                }
            }



        }


        return inflater.inflate(R.layout.fragment_hr_vacancy, container, false)
    }

    private fun showSnackbar(snackbarMsg: String){
        Snackbar.make(
            requireActivity().findViewById<LinearLayout>(R.id.ll_hr_vacancy),
            "Please enter $snackbarMsg.",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}