package com.sun.funnytoeic.data.remote.response

import com.google.gson.annotations.SerializedName

data class BingResponse(
    @SerializedName("value") val value: List<BingImageDetail>
)
