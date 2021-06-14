package uz.najottalim.work_n_hire
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import uz.najottalim.work_n_hire.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.txtGoToRegister.setOnClickListener {
            val action = LoginFragmentDirections.actionGlobalRegisterFragment()
            navigateFragment(action)
        }


        binding.btnLoginWithPhone.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToPhoneVerificationFragment()
            navigateFragment(action)
        }

        binding.login.setOnClickListener {

            Log.d("LoginButton", "onCreate: ")
            when {
                TextUtils.isEmpty(binding.loginEmail.text.toString().trim() { it <= ' ' }) -> {
                    Snackbar.make(
                        requireActivity().findViewById<ConstraintLayout>(R.id.main_cLayout), "Please enter email.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(binding.loginPassword.text.toString().trim() { it <= ' ' }) -> {
                    Snackbar.make(
                        requireActivity().findViewById<ConstraintLayout>(R.id.main_cLayout), "Please enter password.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    val email: String = binding.loginEmail.text.toString().trim() { it <= ' ' }
                    val password: String =
                        binding.loginPassword.text.toString().trim() { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                if (task.isSuccessful) {

                                    Snackbar.make(
                                        binding.loginLayout, "You were registered successfully",
                                        Snackbar.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(activity, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra(
                                        "user_id",
                                        FirebaseAuth.getInstance().currentUser!!.uid
                                    )
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    activity?.finish()
                                } else {
                                    Snackbar.make(
                                        binding.loginLayout,
                                        task.exception!!.message.toString(),
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                }

            }


        }


        return binding.root
    }



    fun navigateFragment(action: NavDirections){
        findNavController().navigate(action)
    }

}