package com.example.fitbit2

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class InputFull(
    @SerialName("inputfull")
    val inputfull: Input?
)

@Keep
@Serializable
data class Input(
    @SerialName("sleepType")
    val sleepType: String?,
    @SerialName("sleepHours")
    val sleepHours: String?,
) : java.io.Serializable
