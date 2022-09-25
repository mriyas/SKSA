package com.suraksha.android.view.fragment.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.suraksha.android.view.fragment.BaseFragment
import com.suraksha.android.view_model.LabsViewModel
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentLabTestAddBinding
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.LabTest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LabTestAddFragment : BaseFragment() {

    lateinit var mBinding: FragmentLabTestAddBinding

    private val mLabsViewModel: LabsViewModel by viewModels()

    private val mArgs: LabTestAddFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentLabTestAddBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        var labTest = mArgs.test
        if (labTest == null) {
            labTest = LabTest()
        }
        mLabsViewModel.mTest.value = labTest
        mBinding.clickHelper = this
        mBinding.test = mLabsViewModel.mTest.value
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //  configureToolbar()
        var title = getString(R.string.create_test)
        if (isEdit()) {
            title = getString(R.string.edit_test)
            setSpinners(mArgs.test)
        }
        setActionBar(title)
        observeUserState()
    }

    private fun setSpinners(test: LabTest?) {
        val spConfidentialityPosition =
            if (test?.confidentiality.isNullOrEmpty()&&test?.confidentiality?.isDigitsOnly()==true) 0 else test?.confidentiality?.toInt() ?: 0
        mBinding.spConfidentiality.setSelection(spConfidentialityPosition)
        val spPrescPosition =
            if (test?.prescription.isNullOrEmpty()&&test?.prescription?.isDigitsOnly()==true) 0 else test?.prescription?.toInt() ?: 0
        mBinding.spPresc.setSelection(spPrescPosition)
        val departments = resources.getStringArray(R.array.departments)
        val spDeptPosition = departments.indexOf(test?.department)
        if (spDeptPosition >= 0) {
            mBinding.spDepartement.setSelection(spDeptPosition)
        }
    }

    override fun onClick(vararg data: Any) {
    }

    override fun onClick(view: View) {
        super.onClick(view)
        when (view.id) {
            R.id.btn_submit -> {
                val test = mLabsViewModel.mTest.value
                test?.department = mBinding.spDepartement.selectedItem.toString()
                test?.confidentiality = mBinding.spConfidentiality.selectedItemPosition.toString()
                test?.prescription = mBinding.spPresc.selectedItemPosition.toString()

                mLabsViewModel.createTest(isEdit())

            }
        }
    }

    private fun observeUserState() {
        lifecycleScope.launch {

            mLabsViewModel.apiState.collect {

                // When state to check the
                // state of received data
                when (it.status) {

                    ApiState.Status.LOADING -> {
                        hideKeyboard()
                    }
                    ApiState.Status.SUCCESS -> {
                        //  showSnackBar("Login Success")

                            goBack()
                        
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
            LabTestAddFragment().apply {

            }
    }

    private fun isEdit(): Boolean {
        return mArgs?.test != null
    }
}