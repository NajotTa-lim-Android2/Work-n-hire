package uz.najottalim.work_n_hire

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import uz.najottalim.work_n_hire.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {



    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)



        binding.register.setOnClickListener {

            when{
                TextUtils.isEmpty(binding.registerEmail.text.toString().trim(){it<=' '}) -> {
                    Snackbar.make(binding.registerLayout, "Please enter email.",
                        Snackbar.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(binding.registerPassword.text.toString().trim(){it<=' '}) -> {
                    Snackbar.make(binding.registerLayout, "Please enter password.",
                        Snackbar.LENGTH_SHORT).show()
                }
                else -> {

                    val email: String = binding.registerEmail.text.toString().trim(){ it <= ' '}
                    val password: String = binding.registerPassword.text.toString().trim(){ it <= ' '}

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                if (task.isSuccessful){

                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Snackbar.make(binding.registerLayout, "You were registered successfully",
                                        Snackbar.LENGTH_SHORT).show()

                                    //val intent = Intent(activity, MainActivity::class.java)
//                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                                    intent.putExtra("user_id", firebaseUser.uid)
//                                    intent.putExtra("email_id", firebaseUser.email)
//                                    startActivity(intent)
                                    val action = RegisterFragmentDirections.actionRegisterFragmentToMainActivity()
                                    navigateFragment(action)
                                    activity?.finish()
                                } else{
                                    Snackbar.make(binding.registerLayout,
                                        task.exception!!.message.toString(),
                                        Snackbar.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                }

            }

        }

        binding.txtGoToLogin.setOnClickListener {
            val action = RegisterFragmentDirections.actionGlobalLoginFragment()
            navigateFragment(action)
        }

        binding.btnRegisterWithPhone.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToPhoneVerificationFragment2()
            navigateFragment(action)
        }
        return binding.root
    }


    fun navigateFragment(action: NavDirections){
        findNavController().navigate(action)
    }

}