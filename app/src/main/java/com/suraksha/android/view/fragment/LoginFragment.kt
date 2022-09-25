package com.suraksha.android.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.suraksha.android.view_model.UserViewModel
import com.suraksha.app.databinding.FragmentLoginBinding
import com.suraksha.cloud.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private val mViewModel: UserViewModel by viewModels()

    private lateinit var mBinding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLoginBinding.inflate(inflater, container, false)
        mBinding.viewModel = mViewModel
        mBinding.lifecycleOwner=viewLifecycleOwner
        return mBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(mViewModel.isUserLoggedIn()){
           checkLoginType()
        }
       // mViewModel.isUserLoggedIn()
       // navigate(LoginFragmentDirections.goToLabsHomeScreen())

        observeUserState()
    }

    private fun checkLoginType() {
        val handler=Handler(Looper.myLooper()!!)
        handler.postDelayed(Runnable {
            val userLogin=mViewModel.getLoggedInService()
            val type=userLogin.second
            val service=userLogin.first
            // type=1 super admin, 2 admin, 3 vendor, 4 end user
            //service=2 lab, 1 pharmacy
            if(type==3) {
                when (service) {
                    2 -> {
                        navigate(LoginFragmentDirections.goToLabsHomeScreen())
                        return@Runnable
                    }
                    1 -> {
                        navigate(LoginFragmentDirections.goToPharmacy())
                        return@Runnable

                    }
                    else -> {
                        showSnackBar("This user login not yet supported")
                    }
                }
            }else{
                showSnackBar("This user login not yet supported")
            }

        },50)




    }

    override fun onClick(vararg data: Any) {
        TODO("Not yet implemented")
    }

    private fun observeUserState() {
        lifecycleScope.launch {

            mViewModel.apiState.collect {

                // When state to check the
                // state of received data
                when (it.status) {

                    ApiState.Status.LOADING -> {
                        hideKeyboard()
                    }
                    ApiState.Status.SUCCESS -> {
                     //  showSnackBar("Login Success")
                        checkLoginType()
                    }
                    ApiState.Status.ERROR -> {
                        showSnackBarError(it.error?.errorMessage)

                    }
                    ApiState.Status.IDLE -> {

                    }

                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {

            }
    }
}