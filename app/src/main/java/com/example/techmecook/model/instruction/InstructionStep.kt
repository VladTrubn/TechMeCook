package com.example.techmecook.model.instruction

import com.squareup.moshi.Json

data class InstructionStep (
    @Json(name = "number") val number: Int,
    @Json(name = "step") val step: String,
)

