package com.felipemdrs.roomaula63.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Civilizations")
data class CivilizationEntity (
    @PrimaryKey
    var id: Int,
    @ColumnInfo
    var name: String
)