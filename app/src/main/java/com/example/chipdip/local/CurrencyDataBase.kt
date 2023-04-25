package com.example.chipdip.local

import android.content.Context
import androidx.room.*
import com.example.chipdip.local.converter.ValuteConverter


@Database(entities = [CurrencyEntity::class], version = 2)
@TypeConverters(ValuteConverter::class)
abstract class CurrencyDataBase : RoomDatabase() {
    abstract fun modelDbDao(): CurrencyDao


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
