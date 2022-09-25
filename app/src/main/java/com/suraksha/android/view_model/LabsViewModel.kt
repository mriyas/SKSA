package com.suraksha.android.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.suraksha.android.SurakshaApplication
import com.suraksha.android.repository.LabsRepository
import com.suraksha.cloud.ApiState
import com.suraksha.cloud.model.APIError
import com.suraksha.cloud.model.response.lab.CreateTestsResponse
import com.suraksha.cloud.model.LabTest
import com.suraksha.cloud.model.response.lab.LabsTestsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Anees on 28-03-2022
 */
@HiltViewModel
class LabsViewModel @Inject constructor(
    private val application: SurakshaApplication,
    private val labsRepository: LabsRepository
) : BaseViewModel(application) {


    //  val mUser: LiveData<SurakshaUser> get() = _mUser

    val mTest=MutableLiveData(LabTest())

    init {

        // isValidUser()
    }



     fun getLabTests(index:Int,pageSize:Int) {
        if (true) {//todo need validation
            //    mobileNumber = countryCode + mobileNumberWithoutCode.value

            viewModelScope.launch {

                labsRepository.getTests(index, pageSize)

                    .catch {
                        apiState.value =
                            ApiState.error(APIError(-1, it.localizedMessage))
                    }

                    .collect {

                        when (it.status) {
                            ApiState.Status.SUCCESS -> {

                                if(it.data is LabsTestsResponse) {
                                   val data = it.data as LabsTestsResponse
                                    apiState.value = ApiState.success(data.tests)

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

    fun getLabTestDetails(id:String) {
        if (true) {//todo need validation
            //    mobileNumber = countryCode + mobileNumberWithoutCode.value

            viewModelScope.launch {

                labsRepository.getTestDetails(id)

                    .catch {
                        apiState.value =
                            ApiState.error(APIError(-1, it.localizedMessage))
                    }

                    .collect {

                        when (it.status) {
                            ApiState.Status.SUCCESS -> {

                                if(it.data is LabTest) {
                                    val data = it.data as LabTest
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

                labsRepository.createTest(mTest.value!!,isEdit)

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