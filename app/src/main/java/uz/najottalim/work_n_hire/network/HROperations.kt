package uz.najottalim.work_n_hire.network

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import uz.najottalim.work_n_hire.Vacancy
import uz.najottalim.work_n_hire.hr.models.HRVacancy
import uz.najottalim.work_n_hire.hr.models.Message
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HROperations(val hruid: String) {

    private val db = Firebase.firestore
    val messageManager = HrMessageManager(hruid)
    /**
     * Also calling this function you can get candidates
     */

    suspend fun getHRVacancyByUid(vacancyUid: String): HRVacancy {
        return withContext(Dispatchers.IO) {
            var hrVacancy: HRVacancy? = null
            db.collection("HRs").document(hruid)
                .collection("vacancies").document(vacancyUid)
                .get().addOnSuccessListener {
                    hrVacancy = it.toObject<HRVacancy>()
                }
            hrVacancy?.vacancy = getVacancyByUid(vacancyUid)
            return@withContext hrVacancy!!
        }
    }


    fun addVacancy(hrVacancy: HRVacancy) {
        db.collection("vacancies").add(hrVacancy.vacancy!!).addOnSuccessListener {
            hrVacancy.vacancyUid = it.id
            hrVacancy.vacancy?.uid = it.id
            db.collection("vacancies").document(it.id).update("uid", it.id)

            val vacancyPath = hrVacancy.vacancy!!.hrUid?.let { it1 ->
                db.collection(HRConstants.COLLECTION_HRS).document(it1)
                    .collection(HRConstants.COLLECTION_VACANCIES).document(hrVacancy.vacancyUid!!)
            }
            hrVacancy.vacancy = null
            vacancyPath?.set(
                hrVacancy
            )

        }.addOnFailureListener {
            it.printStackTrace()
        }
    }

    private suspend fun getVacancyByUid(vacancyUid: String): Vacancy {
        return withContext(Dispatchers.IO) {
            return@withContext async {
                var vacancy: Vacancy? = null
                db.collection("vacancies").document(vacancyUid).get().addOnSuccessListener {
                    vacancy = it.toObject<Vacancy>()
                }
                vacancy!!
            }.await()
        }
    }

    inner class HrMessageManager(val hruid: String) : BaseMessageManager() {

        fun sendMessage(candidateUid: String, vacancyUid: String, text: String) {
            sendMessage(candidateUid, vacancyUid, text, hruid)
        }

        fun initialSendMessage(candidateUid: String, vacancyUid: String, text: String) {
            initialSendMessage(hruid, candidateUid, vacancyUid, text)
        }

        suspend fun getMessages(vacancyUid: String, candidateUid: String): List<Message> =
            suspendCoroutine { continuation ->
                db.collection(HRConstants.COLLECTION_HRS).document(hruid)
                    .collection(HRConstants.COLLECTION_VACANCIES).document(vacancyUid)
                    .collection(HRConstants.COLLECTION_CANDIDATES)
                    .document(candidateUid)
                    .collection(HRConstants.COLLECTION_MESSAGES)
                    .get().addOnSuccessListener {
                        continuation.resume(it.toObjects())
                    }
            }
    }
}
