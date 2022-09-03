package com.suraksha.cloud.model.request


/**
 * Base request
 *
 * @constructor Create empty Base request
 */
interface BaseRequest {
    var token: String?

    fun getHeaderTokenForAuth() : HashMap<String, String?>{
        var map  = getHeader()
        map.put("authorization", "Bearer $token")
        return map
    }

    fun getHeader() : HashMap<String, String?>{
        var map  = HashMap<String, String?>()
        map.put("Accept","application/json")
        map.put("Content-type","application/json")
        return map
    }

    fun getQueryParams(start : String?, limit : Int?) : HashMap<String, String?>{
        var map  = HashMap<String, String?>()
        map.put("start",start)
        map.put("limit",""+limit)
        return map
    }




}