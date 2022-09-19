package com.suraksha.android.view.fragment.lab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.suraksha.android.view.fragment.BaseFragment
import com.suraksha.android.view_model.LabsViewModel
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentLabTestDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LabTestDetailsFragment : BaseFragment() {

    lateinit var mBinding: FragmentLabTestDetailsBinding

    private val mLabsViewModel: LabsViewModel by viewModels()

    private val mArgs:LabTestDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentLabTestDetailsBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.test=mArgs.test
        mBinding.clickHelper=this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  configureToolbar()
        setActionBar(mArgs.test?.testId)

    }

    override fun onClick(vararg data: Any) {
    }

    override fun onClick(view: View) {
        super.onClick(view)
        when(view?.id){
            R.id.iv_edit->{
                navigate(LabTestDetailsFragmentDirections.goToEdit(mBinding.test))
            }
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            LabTestDetailsFragment().apply {

            }
    }
}