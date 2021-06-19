package uz.najottalim.work_n_hire.hr.models

import uz.najottalim.work_n_hire.Vacancy

data class HRVacancy(
    val vacancy: Vacancy,
    val candidates: MutableList<Candidate>,
)