package com.felipemdrs.roomaula63.data.api.endpoint

import com.felipemdrs.roomaula63.data.api.ApiUtils
import com.felipemdrs.roomaula63.data.api.model.CivilizationResponse
import retrofit2.http.GET

interface CivilizationEnpoint {
    @GET("civilizations")
    suspend fun obterLista(): CivilizationResponse

    companion object {
        val endpoint: CivilizationEnpoint by lazy {
            ApiUtils.getRetrofitInstance().create(CivilizationEnpoint::class.java)
        }
    }
}