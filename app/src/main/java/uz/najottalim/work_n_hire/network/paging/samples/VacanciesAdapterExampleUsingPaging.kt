package uz.najottalim.work_n_hire.network.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.najottalim.work_n_hire.Vacancy

class VacanciesAdapter :
    PagingDataAdapter<Vacancy, VacanciesAdapter.ViewHolderVacancy>(VacanciesCallback()) {


    override fun onBindViewHolder(holder: ViewHolderVacancy, position: Int) {
//        holder.binding.companyName.text = getItem(position)?.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVacancy {
        return ViewHolderVacancy(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_vacancy, parent, false)
        )
    }

    inner class ViewHolderVacancy(view: View) : RecyclerView.ViewHolder(view) {
//        val binding = ItemVacancyBinding.bind(view)
    }
}

class VacanciesCallback : DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem.uid == newItem.uid
    }

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem == newItem
    }

}
