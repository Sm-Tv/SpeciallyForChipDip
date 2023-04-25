package com.example.chipdip.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg currencyEntity: CurrencyEntity)

    @Query("SELECT * FROM Currency ORDER BY uid DESC")
    fun getAllLiveData(): LiveData<CurrencyEntity>

    @Query("DELETE FROM Currency")
    fun deleteAllNotes()
}
