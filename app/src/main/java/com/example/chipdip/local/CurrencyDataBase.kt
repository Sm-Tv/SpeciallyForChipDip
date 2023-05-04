package com.example.chipdip.local

import android.content.Context
import androidx.room.*
import com.example.chipdip.local.converter.ValuteConverter
import com.example.chipdip.local.model.CurrencyEntity
import com.example.chipdip.local.model.ItemValueEntity


@Database(entities = [CurrencyEntity::class, ItemValueEntity::class], version = 6, exportSchema = true)
@TypeConverters(ValuteConverter::class)
abstract class CurrencyDataBase : RoomDatabase() {
    abstract fun modelDbDao(): CurrencyDao
    abstract fun modelDbDaoo(): CurrencyDaoo

    companion object {
        @Volatile
        private var INSTANCE: CurrencyDataBase? = null

        fun getDatabase(context: Context): CurrencyDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyDataBase::class.java,
                    "ChipDip_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
