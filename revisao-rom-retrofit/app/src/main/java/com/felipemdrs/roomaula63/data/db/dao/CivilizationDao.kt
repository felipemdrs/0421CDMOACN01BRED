package com.felipemdrs.roomaula63.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.felipemdrs.roomaula63.data.db.entity.CivilizationEntity

@Dao
interface CivilizationDao {
    @Query("SELECT * FROM Civilizations")
    suspend fun obterLista(): List<CivilizationEntity>

    @Transaction
    @Insert(onConflict = REPLACE)
    suspend fun addAllCivilizations(civilization: List<CivilizationEntity>)
}