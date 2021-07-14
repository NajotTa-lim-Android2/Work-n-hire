package uz.najottalim.work_n_hire

import com.google.firebase.Timestamp
import uz.najottalim.work_n_hire.hr.models.CompanyInfo

data class Vacancy(
    var uid: String? = null,
    val hrUid: String? = null,
    val companyInfo: CompanyInfo? = null,
    val title: String? = null,
    val requiredSills: MutableList<String>? = null,
    val salaryFrom: Int? = null,
    var salaryTo: Int? = null,
    val addition: String? = null,
    val publicationTime: Timestamp? = null
)
