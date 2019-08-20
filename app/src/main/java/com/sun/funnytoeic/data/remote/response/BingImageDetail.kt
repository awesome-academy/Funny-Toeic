package com.sun.funnytoeic.data.remote.response

import com.google.gson.annotations.SerializedName

data class BingImageDetail(
    @SerializedName("contentUrl") val contentUrl: String,
    @SerializedName("thumbnailUrl") val thumbnailUrl: String
)
