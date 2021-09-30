package com.felipemdrs.roomaula63.home.repository.db

import com.felipemdrs.roomaula63.data.db.dao.CivilizationDao
import com.felipemdrs.roomaula63.data.db.entity.CivilizationEntity
import com.felipemdrs.roomaula63.home.model.CivilizationModel
import com.felipemdrs.roomaula63.home.repository.ICivilizationRepository

class CivilizationDbRepository(private val civilizationDao: CivilizationDao): ICivilizationRepository {
    suspend fun addAllCivilizations(civilizations: List<CivilizationEntity>) = civilizationDao.addAllCivilizations(civilizations)

    override suspend fun obterLista(): List<CivilizationModel> {
        val result = civilizationDao.obterLista()

        return result.map {
            CivilizationModel(it.id, it.name)
        }
    }
}