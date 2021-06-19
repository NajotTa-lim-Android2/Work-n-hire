package uz.najottalim.work_n_hire

import uz.najottalim.work_n_hire.hr.models.CompanyInfo

data class Vacancy(
    val uid: String,
    val hr_uid: String,
    val companyInfo: CompanyInfo,
    val title: String,
    val required_sills: MutableList<String>,
    val salaryFrom: Int,
    var salaryTo: Int,
    val addition: String,
    val publication_time: Long
)
