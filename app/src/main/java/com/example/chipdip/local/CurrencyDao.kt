package com.example.chipdip.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.chipdip.local.model.CurrencyEntity
import com.example.chipdip.local.model.CurrencyWithValue
import com.example.chipdip.local.model.ItemValueEntity


@Dao
interface CurrencyDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(vararg currencyEntity: CurrencyEntity)

//    @Query("SELECT * FROM Currency ORDER BY uid DESC")
//    fun getAllLiveData(): LiveData<CurrencyEntity>

    @Query("DELETE FROM Currency")
    fun deleteAllCurrency()

    @Query("DELETE FROM ItemValue")
    fun deleteAllItemValue()

    @Query("SELECT * FROM Currency ORDER BY uid DESC")
    fun getAllLiveData(): LiveData<CurrencyWithValue>

//    @Transaction
//    @Query("SELECT * FROM DialogPojo WHERE id = :dialogId")
//    fun getDialogBy(dialogId: String?): DialogWithTags?
}

@Dao
abstract class CurrencyDaoo{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(currencyWithValue: CurrencyWithValue) {
        insert(currencyWithValue.currencyEntity)
        for (tag in currencyWithValue.listItemValueEntity) {
            insert(tag)
        }
    }

//    @Transaction
//    fun insert(list: List <ItemValueEntity>){
//        for (tag in list) {
//            insert(tag)
//        }
//    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(currencyEntity: CurrencyEntity?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(itemValueEntity: ItemValueEntity?)
}
