package uz.najottalim.work_n_hire.specialist.models

import uz.najottalim.work_n_hire.Vacancy
import uz.najottalim.work_n_hire.hr.models.Message

data class AppliedVacancy(
    val vacancyUid: String,
    val messages: MutableList<Message>,
    val vacancy: Vacancy? = null
)