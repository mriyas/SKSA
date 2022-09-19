package com.suraksha.android.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.suraksha.android.view_model.UserViewModel
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentLoginBinding
import com.suraksha.cloud.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
            navigate(LoginFragmentDirections.goToLabsHomeScreen())
            return
        }

        observeUserState()
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
                        navigate(LoginFragmentDirections.goToLabsHomeScreen())
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