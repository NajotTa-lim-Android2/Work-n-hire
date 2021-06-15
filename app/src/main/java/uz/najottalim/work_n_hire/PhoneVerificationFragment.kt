package uz.najottalim.work_n_hire

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import uz.najottalim.work_n_hire.databinding.FragmentPhoneVerificationBinding
import uz.najottalim.work_n_hire.databinding.FragmentRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class PhoneVerificationFragment : Fragment() {


    private var _binding: FragmentPhoneVerificationBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private var verificationCode = ""

    lateinit var credential: PhoneAuthCredential

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPhoneVerificationBinding.inflate(layoutInflater, container, false)
        auth = Firebase.auth


        var phoneNumber = ""
        binding.btnSendCodeAndLogin.setOnClickListener {
            if (binding.tilSmsCode.isGone) {
                when {
                    TextUtils.isEmpty(
                        binding.edtPhoneNumber.text.toString().trim() { it <= ' ' }) -> {
                        Snackbar.make(
                            binding.phoneVerificationLayout, "Please enter email.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    else -> {

                        phoneNumber =
                            binding.edtPhoneNumber.text.toString().trim() { it <= ' ' }

                        val options = activity?.let {
                            PhoneAuthOptions.newBuilder(auth)
                                .setPhoneNumber(phoneNumber)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(it)
                                .setCallbacks(callbacks)// Activity (for callback binding)
                                .build()
                        }

                        if (options != null) {
                            PhoneAuthProvider.verifyPhoneNumber(options)
                        }
                        binding.tilSmsCode.visibility = View.VISIBLE


                    }
                }
            } else {
                when {
                    TextUtils.isEmpty(binding.edtSmsCode.text.toString().trim() { it <= ' ' }) -> {
                        Snackbar.make(
                            binding.phoneVerificationLayout, "Please enter email.",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    } else -> {
                    verificationCode = binding.edtSmsCode.text.toString().trim() {it <= ' '}
                    credential = PhoneAuthProvider.getCredential(storedVerificationId!!, verificationCode)

                    signInWithPhoneAuthCredential(credential)
                }
                }
            }

        }



        callbacks =
            object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    Log.d("TAG", "onVerificationCompleted:$credential")
                    signInWithPhoneAuthCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Log.w("TAG", "onVerificationFailed", e)

                    if (e is FirebaseAuthInvalidCredentialsException) {

                    } else if (e is FirebaseTooManyRequestsException) {

                    }

                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    Log.d("TAG", "onCodeSent:$verificationId")

                    storedVerificationId = verificationId
                    resendToken = token

                }

                override fun onCodeAutoRetrievalTimeOut(p0: String) {
                    super.onCodeAutoRetrievalTimeOut(p0)
                }
            }


        return binding.root
    }





    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        activity?.let {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "signInWithCredential:success")

                        val user = task.result?.user

                        val action = PhoneVerificationFragmentDirections
                            .actionPhoneVerificationFragmentToMainActivity()
                        navigateFragment(action)
                    } else {
                        Log.w("TAG", "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        }
                    }
                }
        }
    }


    companion object {
        private const val TAG = "PhoneAuthActivity"
    }



    fun navigateFragment(action: NavDirections){
        findNavController().navigate(action)
    }

}