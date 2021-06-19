package uz.najottalim.work_n_hire.specialist.models

data class ProfileInfo(
    val fullName: String,
    val email: String,
    val headline: String,
    val address: String,
    val profileImg: String,
    val resumeUrl: String,
    val minExpectedSalary: Int,
    val skills: MutableList<String>,
    val educationList: MutableList<EducationItem>,
    val experienceList: MutableList<ExperienceItem>,
    val languages: MutableList<Language>,
)
