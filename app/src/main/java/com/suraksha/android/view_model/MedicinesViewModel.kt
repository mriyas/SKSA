package com.suraksha.android.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.suraksha.android.SurakshaApplication
import com.suraksha.android.repository.MedicineRepository
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.APIError
import com.suraksha.cloud.model.Bookings
import com.suraksha.cloud.model.response.lab.CreateTestsResponse
import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.model.response.lab.BookingListResponse
import com.suraksha.cloud.model.response.lab.MedicineListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Anees on 28-03-2022
 */
@HiltViewModel
class MedicinesViewModel @Inject constructor(
    private val application: SurakshaApplication,
    private val medicineRepository: MedicineRepository
) : BaseViewModel(application) {


    //  val mUser: LiveData<SurakshaUser> get() = _mUser

    val mTest=MutableLiveData(LabTest())

    init {

        // isValidUser()
    }



     fun getMedicines(index:Int,pageSize:Int) {
        if (true) {//todo need validation
            //    mobileNumber = countryCode + mobileNumberWithoutCode.value

            viewModelScope.launch {

                medicineRepository.getMedicines(index, pageSize)

                    .catch {
                        apiState.value =
                            ApiState.error(APIError(-1, it.localizedMessage))
                    }

                    .collect {

                        when (it.status) {
                            ApiState.Status.SUCCESS -> {

                                if(it.data is MedicineListResponse) {
                                   val data = it.data as MedicineListResponse
                                    apiState.value = ApiState.success(data.bookings)

                                }

                            }
                            ApiState.Status.ERROR -> {
                                apiState.value =
                                    ApiState.error(it.error)
                            }
                            ApiState.Status.LOADING -> {
                                //  errorModel.uiUpdate = true
                                apiState.value = ApiState.loading()
                            }
                            ApiState.Status.IDLE -> {
                                apiState.value = ApiState.idle()

                            }
                        }
                    }
            }


        } else {
            //      userLiveData?.postMobileValidationFailed()
        }
    }

    fun getMedicineDetails(id:String) {
        if (true) {//todo need validation
            //    mobileNumber = countryCode + mobileNumberWithoutCode.value

            viewModelScope.launch {

                medicineRepository.getMedicineDetails(id)

                    .catch {
                        apiState.value =
                            ApiState.error(APIError(-1, it.localizedMessage))
                    }

                    .collect {

                        when (it.status) {
                            ApiState.Status.SUCCESS -> {

                                if(it.data is Bookings) {
                                    val data = it.data as Bookings
                                    apiState.value = ApiState.success(data)

                                }

                            }
                            ApiState.Status.ERROR -> {
                                apiState.value =
                                    ApiState.error(it.error)
                            }
                            ApiState.Status.LOADING -> {
                                //  errorModel.uiUpdate = true
                                apiState.value = ApiState.loading()
                            }
                            ApiState.Status.IDLE -> {
                                apiState.value = ApiState.idle()

                            }
                        }
                    }
            }


        } else {
            //      userLiveData?.postMobileValidationFailed()
        }
    }

    fun createTest(isEdit:Boolean=false) {
        if (true) {//todo need validation
            //    mobileNumber = countryCode + mobileNumberWithoutCode.value

            viewModelScope.launch {

                medicineRepository.createTest(mTest.value!!,isEdit)

                    .catch {
                        apiState.value =
                            ApiState.error(APIError(-1, it.localizedMessage))
                    }

                    .collect {

                        when (it.status) {
                            ApiState.Status.SUCCESS -> {

                                if(it.data is CreateTestsResponse) {
                                    val response=it.data as CreateTestsResponse

                                    apiState.value = ApiState.success(response.testId)
                                }

                            }
                            ApiState.Status.ERROR -> {
                                apiState.value =
                                    ApiState.error(it.error)
                            }
                            ApiState.Status.LOADING -> {
                                //  errorModel.uiUpdate = true
                                apiState.value = ApiState.loading()
                            }
                            ApiState.Status.IDLE -> {
                                apiState.value = ApiState.idle()

                            }
                        }
                    }
            }


        } else {
            //      userLiveData?.postMobileValidationFailed()
        }
    }

}