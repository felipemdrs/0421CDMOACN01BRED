package com.felipemdrs.roomaula63.data.api.model

import com.google.gson.annotations.SerializedName

data class CivilizationResponse (
    val civilizations: List<CivilizationApiModel>
)

data class CivilizationApiModel (
    val id: Int,
    val name: String
)