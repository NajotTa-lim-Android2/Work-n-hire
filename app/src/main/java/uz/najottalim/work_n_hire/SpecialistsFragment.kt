package uz.najottalim.work_n_hire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.najottalim.work_n_hire.databinding.FragmentSpecialistsBinding
import uz.najottalim.work_n_hire.profile_info.Education
import uz.najottalim.work_n_hire.profile_info.Experience
import uz.najottalim.work_n_hire.profile_info.General
import uz.najottalim.work_n_hire.profile_info.ProfileInfo


class SpecialistsFragment : Fragment() {

    private lateinit var _binding: FragmentSpecialistsBinding

    private lateinit var specialistsRecyclerViewAdapter: SpecialistsRecyclerViewAdapter

    private lateinit var profileInfo: ArrayList<ProfileInfo>

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileInfo = ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSpecialistsBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileInfo.add(
            ProfileInfo(
                General(
                    "Ali Madaminov",
                    "Android developer",
                    "1+",
                    "1000$",
                    "Ali is Senior Developer",
                    "https://www.google.com/url?https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Muhammad_Ali_NYWTS.jpg/1200px-Muhammad_Ali_NYWTS.jpg",
                    "Uzbekistan, Tashkent"
                ),
                Experience("Google", "2004.04.06", "Senior Adnroid developer", "Present"),
                Education("Android development", "Senior", "2004.04.06", "Najot ta'lim", "Present"),
                arrayListOf<String>(
                    "Java",
                    "Kotlin",
                    "Python",
                    "Android Jetpack",
                    "SOLID",
                    "MVVM",
                    "Kotlin Coroutines",
                    "RxJava",
                    "Firebase",
                    "AdMob",
                    "Git",
                    "Okhttp",
                    "Retrofit2",
                    "Dagger2",
                    "Sqlite"
                ),
                1
            )
        )

        profileInfo.add(
            ProfileInfo(
                General(
                    "Ali Madaminov",
                    "Android developer",
                    "1+",
                    "1000$",
                    "Ali is Senior Developer",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/8/89/Muhammad_Ali_NYWTS.jpg/1200px-Muhammad_Ali_NYWTS.jpg",
                    "Uzbekistan, Tashkent"
                ),
                Experience("Google", "2004.04.06", "Senior Adnroid developer", "Present"),
                Education("Android development", "Senior", "2004.04.06", "Najot ta'lim", "Present"),
                arrayListOf<String>(
                    "Java",
                    "Kotlin",
                    "Python",
                    "Android Jetpack",
                    "SOLID",
                    "MVVM",
                    "Kotlin Coroutines",
                    "RxJava",
                    "Firebase",
                    "AdMob",
                    "Git",
                    "Okhttp",
                    "Retrofit2",
                    "Dagger2",
                    "Sqlite"
                ),
                2
            )
        )

        specialistsRecyclerViewAdapter =
            SpecialistsRecyclerViewAdapter(profileInfoCallBack = SpecialistsRecyclerViewAdapter.ProfileInfoCallBack { profileInfoItem ->

                val directions =
                    SpecialistsFragmentDirections.actionSpecialistsFragmentToMoreInfoFragment(
                        profileInfoItem.id
                    )

                findNavController().navigate(directions)

            })

        specialistsRecyclerViewAdapter.submitList(profileInfo)

        binding.specialistsRecyclerView.apply {

            layoutManager = LinearLayoutManager(requireActivity())
            adapter = specialistsRecyclerViewAdapter

        }

    }

}