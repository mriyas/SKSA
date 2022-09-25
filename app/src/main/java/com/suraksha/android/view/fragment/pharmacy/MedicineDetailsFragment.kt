package com.suraksha.android.view.fragment.pharmacy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.suraksha.android.view.fragment.BaseFragment
import com.suraksha.android.view_model.MedicinesViewModel
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentMedicineDetailsBinding
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.model.Medicines
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MedicineDetailsFragment : BaseFragment() {

    lateinit var mBinding: FragmentMedicineDetailsBinding

    private val mLabsViewModel: MedicinesViewModel by viewModels()

    private val mArgs:MedicineDetailsFragmentArgs by navArgs()

    private val mTest=MutableLiveData<Medicines?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentMedicineDetailsBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mTest.value=mArgs.medicine
        mBinding.bookings=mTest.value
        mBinding.clickHelper=this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  configureToolbar()
        setActionBar(mArgs.medicine?.medicineId)
        mArgs.medicine?.medicineId?.let { mLabsViewModel.getMedicineDetails(it) }
        observeData()
    }

    override fun onClick(vararg data: Any) {
    }

    override fun onClick(view: View) {
        super.onClick(view)
        when(view?.id){
            R.id.iv_edit->{
             //   navigate(LabTestDetailsFragmentDirections.goToEdit(mTest.value))
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {

            mLabsViewModel.apiState.collect {

                // When state to check the
                // state of received data
                when (it.status) {

                    ApiState.Status.LOADING -> {
                        // hideKeyboard()
                    }
                    ApiState.Status.SUCCESS -> {
                        // showSnackBar("Login Success")
                        val data = it.data
                        if (data!=null&&data is Medicines) {
                          mTest.postValue(data)
                        }
                    }
                    ApiState.Status.ERROR -> {
                        // showSnackBarError(it.error?.errorMessage)

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
            MedicineDetailsFragment().apply {

            }
    }
}