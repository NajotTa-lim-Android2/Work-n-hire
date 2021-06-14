package uz.najottalim.work_n_hire

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.najottalim.work_n_hire.databinding.SpecialistsItemLayoutBinding
import uz.najottalim.work_n_hire.profile_info.ProfileInfo

class SpecialistsRecyclerViewAdapter(private val profileInfoCallBack: ProfileInfoCallBack) :
    ListAdapter<ProfileInfo, SpecialistsRecyclerViewAdapter.ViewHolder>(SpecialistDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            SpecialistsItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val profileInfo = getItem(position)

        holder.binding.apply {

            tvSpecialistField.text = profileInfo.experience.position

            tvSpecialistName.text = profileInfo.general.lNameFname

            tvSpecialistLocation.text = profileInfo.general.location

        }


        holder.binding.root.setOnClickListener {

            profileInfoCallBack.onClick(getItem(position))

        }

    }

    class ViewHolder(val binding: SpecialistsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    class SpecialistDiffUtil : DiffUtil.ItemCallback<ProfileInfo>() {

        override fun areItemsTheSame(oldItem: ProfileInfo, newItem: ProfileInfo): Boolean {

            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProfileInfo, newItem: ProfileInfo): Boolean {

            return oldItem == newItem
        }


    }

    class ProfileInfoCallBack(val profileInfoCallBack: (profileInfo: ProfileInfo) -> Unit) {
        fun onClick(profileInfo: ProfileInfo) = profileInfoCallBack(profileInfo)
    }
}