package com.example.techmecook.model.instruction

import com.squareup.moshi.Json

data class Instruction (
    @Json(name = "name") val name: String,
    @Json(name = "steps") val steps: List<InstructionStep>
)


