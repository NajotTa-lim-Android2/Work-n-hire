package uz.najottalim.work_n_hire.specialist.models

import uz.najottalim.work_n_hire.Vacancy

data class FavouriteVacancy(val vacancyUid: String, var vacancy: Vacancy? = null)
