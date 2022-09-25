package com.suraksha.android.view.fragment.time_slot

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.suraksha.android.view.fragment.BaseFragment
import com.suraksha.android.view_model.TimeSlotViewModel
import com.suraksha.app.R
import com.suraksha.app.databinding.FragmentTimeSlotAddBinding
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.response.TimeSlot
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class TimeSlotAddFragment : BaseFragment() {

    lateinit var mBinding: FragmentTimeSlotAddBinding

    private val timeSlotViewModel: TimeSlotViewModel by viewModels()

    //private val mArgs: LabTestAddFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentTimeSlotAddBinding.inflate(inflater, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner

        mBinding.clickHelper = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDate(false)

        observeUserState()
        checkFirstSlot()
        checkSecondSlot()
        checkThirdSlot()
        val listener = CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            when (buttonView?.id) {

                R.id.sw_noOfBookings1 -> {
                    checkFirstSlot()
                }
                R.id.sw_noOfBookings2 -> {
                    checkSecondSlot()
                }
                R.id.sw_noOfBookings3 -> {
                    checkThirdSlot()
                }

            }
        }
        mBinding.swNoOfBookings1.setOnCheckedChangeListener(listener)
        mBinding.swNoOfBookings2.setOnCheckedChangeListener(listener)
        mBinding.swNoOfBookings3.setOnCheckedChangeListener(listener)
    }


    override fun onClick(vararg data: Any) {
    }

    override fun onClick(view: View) {
        super.onClick(view)
        when (view.id) {

            R.id.tv_date -> {
                setDate();
            }


            R.id.btn_submit -> {

                timeSlotViewModel.createTimeSlot(getTimeSlots())


            }
        }
    }

    private fun getTimeSlots(): List<TimeSlot> {
        val slots = mutableListOf<TimeSlot>()
        var slot = TimeSlot(45)
        slot.slotRange = "07:00 AM to 10:00 AM"
        slot.status = if (mBinding.swNoOfBookings1.isChecked) 1 else 0
        slot.noOfBookings = mBinding.spNoOfBookings1.selectedItem.toString()
        slots.add(slot)
        slot = TimeSlot(46)
        slot.slotRange = "10:00 AM to 01:00 PM"
        slot.status = if (mBinding.swNoOfBookings2.isChecked) 1 else 0
        slot.noOfBookings = mBinding.spNoOfBookings2.selectedItem.toString()
        slots.add(slot)
        slot = TimeSlot(47)
        slot.slotRange = "02:00 PM to 05:00 PM"
        slot.status = if (mBinding.swNoOfBookings3.isChecked) 1 else 0
        slot.noOfBookings = mBinding.spNoOfBookings3.selectedItem.toString()
        slots.add(slot)
        return slots
    }

    private fun checkFirstSlot() {

        mBinding.spNoOfBookings1.isEnabled = mBinding.swNoOfBookings1.isChecked
    }

    private fun checkSecondSlot() {

        mBinding.spNoOfBookings2.isEnabled = mBinding.swNoOfBookings2.isChecked
    }

    private fun checkThirdSlot() {

        mBinding.spNoOfBookings3.isEnabled = mBinding.swNoOfBookings3.isChecked
    }

    private fun setDate(showDialog: Boolean = true) {
        val splitChar = "/"
        val c: Calendar = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)
        mBinding.tvDate.text = String.format("%02d", mDay) + splitChar + String.format(
            "%02d",
            mMonth + 1
        ) + splitChar + mYear
        if (!showDialog) {
            return
        }


        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                run {
                    val mMonth = String.format("%02d", (monthOfYear + 1))
                    val mDay = String.format("%02d", dayOfMonth)
                    mBinding.tvDate.text =
                        mDay + splitChar + mMonth + splitChar + year
                }
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

    private fun observeUserState() {
        lifecycleScope.launch {

            timeSlotViewModel.apiState.collect {

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


}