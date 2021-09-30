package com.felipemdrs.roomaula63.home.repository.api

import com.felipemdrs.roomaula63.data.api.endpoint.CivilizationEnpoint
import com.felipemdrs.roomaula63.home.model.CivilizationModel
import com.felipemdrs.roomaula63.home.repository.ICivilizationRepository

class CivilizationApiRepository: ICivilizationRepository {
    private val client = CivilizationEnpoint.endpoint

    override suspend fun obterLista(): List<CivilizationModel> {
        val result = client.obterLista()

        return result.civilizations.map {
            CivilizationModel(it.id, it.name)
        }
    }
}