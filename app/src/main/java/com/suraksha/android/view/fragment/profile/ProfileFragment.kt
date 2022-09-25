package com.suraksha.android.view.fragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.suraksha.android.view.activity.SurakshaLaunchActivity
import com.suraksha.android.view.fragment.BaseFragment
import com.suraksha.android.view_model.UserViewModel
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class ProfileFragment : BaseFragment() {
    lateinit var mBinding: FragmentSettingsBinding

    private val mViewModel:UserViewModel by viewModels()

    override fun onClick(view: View) {
        super.onClick(view)
        when(view.id){
            R.id.btn_logout->{
                try {
                    mViewModel.doLogout()
                   // requireActivity().apply {
                      //  finish()
                    val intent = activity?.intent
                    activity?. finish()
                    startActivity(intent)
                      //  startActivity(Intent(requireActivity(), SurakshaLaunchActivity::class.java))
                    //}
                }catch (ex: Exception){
                    ex.printStackTrace()
                }
            }
        }

    }

    override fun onClick(vararg data: Any) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding= FragmentSettingsBinding.inflate( inflater,container, false)
        mBinding.clickHelper=this
        mBinding.lifecycleOwner=viewLifecycleOwner

        return mBinding.root
    }
}