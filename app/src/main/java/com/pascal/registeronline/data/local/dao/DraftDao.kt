package com.pascal.registeronline.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.registeronline.data.local.entity.DraftEntity

@Dao
interface DraftDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertDraft(cachedTest: DraftEntity) : Long

    @Delete
    abstract suspend fun deleteDraft(item: DraftEntity) : Int

    @Query("SELECT * FROM draft")
    abstract suspend fun getDraftList(): List<DraftEntity>?

    @Query("DELETE FROM draft")
    abstract suspend fun clearDraftsTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAllDrafts(cachedTests: List<DraftEntity>) : List<Long>

}