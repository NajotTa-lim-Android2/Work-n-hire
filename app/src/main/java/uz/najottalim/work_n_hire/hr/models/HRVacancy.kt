package uz.najottalim.work_n_hire.hr.models

import uz.najottalim.work_n_hire.Vacancy

data class HRVacancy(
    var vacancyUid: String? = null,
    var vacancy: Vacancy? = null,
    val candidates: MutableList<Candidate>? = null,
)