package com.suraksha.cloud.model.response.auth

import com.suraksha.cloud.model.response.ResponseData

data class ProfileUploadResponse(
    var profileUrl: String = "",
    var requestUrlId: Int = 0
) : ResponseData()

