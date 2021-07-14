package uz.najottalim.work_n_hire.network

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.najottalim.work_n_hire.hr.models.Message
import uz.najottalim.work_n_hire.network.HRConstants
import uz.najottalim.work_n_hire.specialist.PotentialJob

abstract class BaseMessageManager {

    val db = Firebase.firestore

    open fun initialSendMessage(hruid: String, candidateUid: String, vacancyUid: String, text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val message = Message(false, text)
            addInitialMessageSpecialistVersion(candidateUid, vacancyUid, message)
            addMessageHRVersion(hruid = hruid,vacancyUid, message, candidateUid)
        }
    }

    open fun sendMessage(candidateUid: String, vacancyUid: String, text: String, hruid: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val specialistVacancyPath = db.collection("specialists")
                .document(candidateUid)
                .collection("vacancies")
                .document(vacancyUid)

            specialistVacancyPath.collection("messages")
                .add(Message(false, text))

            addMessageHRVersion(
                hruid = hruid,
                vacancyUid, Message(
                    from_candidate = false, text = text
                ),
                candidateUid = candidateUid
            )
        }
    }

    private suspend fun addInitialMessageSpecialistVersion(
        specialistUid: String,
        vacancyUid: String,
        message: Message
    ) {
        withContext(Dispatchers.IO) {
            val specialistVacancyPath = db.collection("specialists")
                .document(specialistUid)
                .collection("vacancies")
                .document(vacancyUid)

            specialistVacancyPath.set(PotentialJob(isApplied = false))
            specialistVacancyPath.collection("messages")
                .add(message)
        }

    }

    private suspend fun addMessageHRVersion(
        hruid: String,
        vacancyUid: String,
        message: Message,
        candidateUid: String
    ) {
        withContext(Dispatchers.IO) {
            db.collection(HRConstants.COLLECTION_HRS).document(hruid)
                .collection(HRConstants.COLLECTION_VACANCIES).document(vacancyUid)
                .collection(HRConstants.COLLECTION_CANDIDATES)
                .document(candidateUid)
                .collection(HRConstants.COLLECTION_MESSAGES)
                .add(message)
        }
    }
}