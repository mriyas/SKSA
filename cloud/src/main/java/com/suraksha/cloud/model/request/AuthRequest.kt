package com.suraksha.cloud.model.request

import com.google.gson.annotations.Expose

class AuthRequest(
    @Expose(serialize = false, deserialize = false)
    override var token: String?
) : BaseRequest {
}