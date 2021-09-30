package com.felipemdrs.roomaula63.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.felipemdrs.roomaula63.data.db.dao.CivilizationDao
import com.felipemdrs.roomaula63.data.db.entity.CivilizationEntity

@Database(
    entities = [CivilizationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseUtils: RoomDatabase() {
    abstract fun civilizationDao(): CivilizationDao

    companion object {
        private var INSTANCE: DatabaseUtils? = null

        fun getDatabase(context: Context): DatabaseUtils {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseUtils::class.java,
                    "aoeii"
                ).build()
            }

            return INSTANCE!!
        }
    }
}