package uz.najottalim.work_n_hire.specialist.models

data class ExperienceItem(
    val companyName: String,
    val position: String,
    val from: Long,
    val to: Long? = null
)