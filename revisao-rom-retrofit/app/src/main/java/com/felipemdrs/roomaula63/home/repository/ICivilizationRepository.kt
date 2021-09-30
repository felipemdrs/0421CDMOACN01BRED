package com.felipemdrs.roomaula63.home.repository

import com.felipemdrs.roomaula63.home.model.CivilizationModel

interface ICivilizationRepository {
    suspend fun obterLista(): List<CivilizationModel>
}