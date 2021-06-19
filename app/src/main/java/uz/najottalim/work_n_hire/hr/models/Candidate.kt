package uz.najottalim.work_n_hire.hr.models

data class Candidate(
    val specialist_uid:String,
    var isApplied: Boolean? = null,
    val messages: MutableList<Message>? = null
)
