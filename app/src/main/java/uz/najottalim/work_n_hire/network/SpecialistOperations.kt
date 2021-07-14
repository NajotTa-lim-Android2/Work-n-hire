package uz.najottalim.work_n_hire.network

import android.os.Message
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import uz.najottalim.work_n_hire.Vacancy
import uz.najottalim.work_n_hire.hr.models.Candidate
import uz.najottalim.work_n_hire.specialist.PotentialJob
import uz.najottalim.work_n_hire.specialist.SpecialistConstants
import uz.najottalim.work_n_hire.specialist.models.FavouriteVacancy
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SpecialistOperations {
    private val db = Firebase.firestore
    private val specialistsPath = db.collection(SpecialistConstants.COLLECTION_SPECIALISTS)
    val messageManager = SpecialistMessageManager()

    @InternalCoroutinesApi
    suspend fun getPotentialJobs(currentSpecialistUid: String): List<PotentialJob> {
        return withContext(Dispatchers.IO) {
            val potentialJobs: MutableList<PotentialJob> = mutableListOf()
            specialistsPath.document(currentSpecialistUid)
                .collection(SpecialistConstants.POTENTIAL_JOBS)
                .get().addOnSuccessListener {
                    for (document in it.documents) {
                        val potentialJob: PotentialJob? = document.toObject()
                        CoroutineScope(Dispatchers.IO).launch {
                            potentialJob?.vacancy = getVacancyByUid(potentialJob?.vacancyUid!!)
                            potentialJobs.add(potentialJob)
                        }
                    }
                }
            return@withContext potentialJobs
        }
    }

    fun addVacancyToFavourites(currentSpecialistUid: String, vacancyUid: String) {
        specialistsPath.document(currentSpecialistUid)
            .collection(SpecialistConstants.COLLECTION_FAVOURITE_VACANCIES)
            .document(vacancyUid)
            .set(FavouriteVacancy(vacancyUid))
    }

    // TODO: 14/07/21 Do with flow
    @InternalCoroutinesApi
    fun getFavouriteVacancies(currentSpecialistUid: String): MutableList<FavouriteVacancy> {
        val favouriteVacancies: MutableList<FavouriteVacancy> = mutableListOf()
        specialistsPath.document(currentSpecialistUid)
            .collection(SpecialistConstants.COLLECTION_FAVOURITE_VACANCIES)
            .get().addOnSuccessListener {
                for (document in it.documents) {
                    val favouriteVacancy = document.toObject<FavouriteVacancy>()
                    CoroutineScope(Dispatchers.IO).launch {
                        favouriteVacancy?.vacancy =
                            favouriteVacancy?.vacancyUid?.let { vacancyUid ->
                                getVacancyByUid(
                                    vacancyUid
                                )
                            }
                        if (favouriteVacancy != null) {
                            favouriteVacancies.add(favouriteVacancy)
                        }
                    }
                }
            }
        return favouriteVacancies
    }

    fun applyForJob(hrUid: String, vacancyUid: String, currentSpecialistUid: String) {
        db.collection(HRConstants.COLLECTION_HRS).document(hrUid)
            .collection(HRConstants.COLLECTION_VACANCIES).document(vacancyUid)
            .collection(HRConstants.COLLECTION_CANDIDATES)
            .add(Candidate(currentSpecialistUid, true))
            .addOnSuccessListener {
                db.collection(SpecialistConstants.COLLECTION_SPECIALISTS)
                    .document(currentSpecialistUid).collection(SpecialistConstants.POTENTIAL_JOBS)
                    .document(vacancyUid)
                    .set(PotentialJob(vacancyUid = vacancyUid, isApplied = true))
            }
    }

    @InternalCoroutinesApi
    private fun getFavouriteVacancies(query: QuerySnapshot): MutableList<FavouriteVacancy> {
        val favouriteVacancies: MutableList<FavouriteVacancy> = mutableListOf()
        for (document in query.documents) {
            val favouriteVacancy = document.toObject<FavouriteVacancy>()
            CoroutineScope(Dispatchers.IO).launch {
                favouriteVacancy?.vacancy =
                    favouriteVacancy?.vacancyUid?.let { vacancyUid ->
                        getVacancyByUid(
                            vacancyUid
                        )
                    }
                if (favouriteVacancy != null) {
                    favouriteVacancies.add(favouriteVacancy)
                }
            }
        }
        return favouriteVacancies
    }

    @InternalCoroutinesApi
    private suspend fun getVacancyByUid(vacancyUid: String): Vacancy? =
        suspendCoroutine { continuation ->
            var vacancy: Vacancy?
            db.collection("vacancies").document(vacancyUid).get().addOnSuccessListener {
                vacancy = it.toObject<Vacancy>()
                continuation.resume(vacancy)
            }
        }


    inner class SpecialistMessageManager : BaseMessageManager() {

        suspend fun getMessages(specialistUid: String, vacancyUid: String): List<Message> =
            suspendCoroutine { continuation ->
                db.collection(SpecialistConstants.COLLECTION_SPECIALISTS)
                    .document(specialistUid)
                    .collection(SpecialistConstants.POTENTIAL_JOBS)
                    .document(vacancyUid)
                    .collection(SpecialistConstants.COLLECTION_MESSAGES)
                    .get().addOnSuccessListener {
                        continuation.resume(it.toObjects())
                    }
            }
    }

}


