package com.istgerade.data.entity

import com.google.gson.annotations.SerializedName

data class NumberResponse(
    val iseven: Boolean? = null,
    val ad: String? = null,
    var num:Long?=null,
    val error:String?=null
)
