package com.suraksha.android.view.fragment.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.suraksha.android.view.fragment.BaseFragment
import com.suraksha.android.view_model.BookingsViewModel
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentBookingDetailsBinding
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.Bookings
import com.suraksha.cloud.model.LabTest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookingDetailsFragment : BaseFragment() {

    lateinit var mBinding: FragmentBookingDetailsBinding

    private val mLabsViewModel: BookingsViewModel by viewModels()

    private val mArgs:BookingDetailsFragmentArgs by navArgs()

    private val mTest=MutableLiveData<Bookings?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentBookingDetailsBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mTest.value=mArgs.booking
        mBinding.bookings=mTest.value
        mBinding.clickHelper=this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  configureToolbar()
        setActionBar(mArgs.booking?.bookingId)
        mArgs.booking?.bookingId?.let { mLabsViewModel.getBookingDetails(it) }
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
                        if (data!=null&&data is Bookings) {
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
            BookingDetailsFragment().apply {

            }
    }
}