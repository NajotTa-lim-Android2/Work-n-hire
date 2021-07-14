package uz.najottalim.work_n_hire.specialist.models

data class Specialist(
    val profileInfo: ProfileInfo? = null,
    val favouriteVacancyUids: MutableList<String>? = null,
    val potentialJobUids: MutableList<String>? = null
)
