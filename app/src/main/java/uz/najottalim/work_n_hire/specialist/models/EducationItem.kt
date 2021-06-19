package uz.najottalim.work_n_hire.specialist.models

//If specialist is still studying there make @to null
data class EducationItem(
    val institution: String? = null,
    val courseName: String,
    val degree: String? = null,
    val from:Long,
    val to:Long? = null
)
