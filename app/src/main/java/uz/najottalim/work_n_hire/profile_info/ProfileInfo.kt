package uz.najottalim.work_n_hire.profile_info

data class ProfileInfo(

    val general: General,

    val experience: Experience,

    val education: Education,

    val skills: ArrayList<String>,

    val id: Int

)
