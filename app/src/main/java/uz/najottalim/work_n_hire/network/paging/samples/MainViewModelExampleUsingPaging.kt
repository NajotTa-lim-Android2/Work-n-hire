package uz.najottalim.work_n_hire.network.paging.samples

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import uz.najottalim.work_n_hire.Vacancy
import uz.najottalim.work_n_hire.network.paging.VacanciesSource

class MainViewModelExampleUsingPaging : ViewModel() {

    val vacancies: Flow<PagingData<Vacancy>> = fetchVacancies()

    fun fetchVacancies(): Flow<PagingData<Vacancy>> {
        val query = Firebase.firestore.collection("HRs").document("some_hr_uid_programmatically5")
            .collection("vacancies").orderBy("publicationTime", Query.Direction.DESCENDING)
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { VacanciesSource() }
        ).flow
    }
}