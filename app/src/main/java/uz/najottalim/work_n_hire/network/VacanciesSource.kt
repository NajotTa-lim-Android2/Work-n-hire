package uz.najottalim.work_n_hire.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firestore.v1.Document
import uz.najottalim.work_n_hire.Vacancy

class VacanciesSource : PagingSource<String, Vacancy>() {

    override fun getRefreshKey(state: PagingState<String, Vacancy>): String? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Vacancy> {
        TODO("Not yet implemented")
    }
}