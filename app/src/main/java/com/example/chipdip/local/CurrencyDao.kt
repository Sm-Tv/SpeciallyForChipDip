package com.example.chipdip.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg currencyEntity: CurrencyEntity)

    @Query("SELECT * FROM Currency ORDER BY date DESC")
    fun getAllLiveData(): LiveData<List<CurrencyEntity>>

    @Query("SELECT * FROM Currency ORDER BY date DESC LIMIT 1")
    fun getLastDateLiveData(): LiveData<CurrencyEntity>

    @Query("DELETE FROM Currency")
    fun deleteAllNotes()

}
