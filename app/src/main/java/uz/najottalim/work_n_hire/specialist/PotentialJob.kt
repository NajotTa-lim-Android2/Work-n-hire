package uz.najottalim.work_n_hire.specialist

import uz.najottalim.work_n_hire.Vacancy
import uz.najottalim.work_n_hire.hr.models.Message

//And there is also messages collection in potential job
data class PotentialJob(
    val vacancyUid:String? = null,
    var vacancy: Vacancy? = null,
    val isApplied: Boolean? = null
) {
}