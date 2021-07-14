package uz.najottalim.work_n_hire.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import uz.najottalim.work_n_hire.Vacancy
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *  You can also create another paging source,
 *  copy this code and just change type argument, and queries
 */

class VacanciesSource : PagingSource<QuerySnapshot, Vacancy>() {
    private val db = Firebase.firestore

    override fun getRefreshKey(state: PagingState<QuerySnapshot, Vacancy>): QuerySnapshot? {
        return null
    }

    @InternalCoroutinesApi
    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, Vacancy> {

        val currentPage: QuerySnapshot = getCurrentPage(params)

        val lastDocumentSnapshot =
            currentPage.documents.get(currentPage.size() - 1)

        val nextPage: QuerySnapshot? = getNextPage(lastDocumentSnapshot, params.loadSize)

        var vacancies: MutableList<Vacancy> = mutableListOf()
        if (!currentPage.isEmpty) {
            for (document in currentPage.documents) {
                val vacancy = document.toObject<Vacancy>()
                if (vacancy != null) {
                    vacancies.add(vacancy)
                }
            }
        }

        return try {
            LoadResult.Page(
                data = vacancies,
                prevKey = null,
                nextKey = if (vacancies.size < params.loadSize || params.key == nextPage) null else nextPage
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }

    }

    private suspend fun getCurrentPage(params: LoadParams<QuerySnapshot>): QuerySnapshot =
        suspendCoroutine { continuation ->
            if (params.key != null) {
                continuation.resume(params.key!!)
            } else {
                db.collection("vacancies")
                    .orderBy("publicationTime", Query.Direction.DESCENDING)
                    .limit(params.loadSize.toLong())
                    .get()
                    .addOnSuccessListener {
                        continuation.resume(it)
                    }.addOnFailureListener {
                        continuation.resumeWithException(it)
                    }
            }
        }

    private suspend fun getNextPage(
        lastDocumentSnapshot: DocumentSnapshot?,
        loadSize:Int
    ): QuerySnapshot? =
        suspendCoroutine { continuation ->
            db.collection("vacancies").orderBy("publicationTime", Query.Direction.DESCENDING)
                .limit(loadSize.toLong())
                .startAfter(lastDocumentSnapshot)
                .get()
                .addOnSuccessListener {
                    continuation.resume(it)
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
}