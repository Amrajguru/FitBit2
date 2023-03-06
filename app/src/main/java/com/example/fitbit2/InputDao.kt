package com.example.fitbit2
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InputDao {
    @Query("SELECT * FROM input_table")
    fun getAll(): Flow<List<InputEntity>>

    @Insert
    fun insert(inputs: InputEntity)

    @Insert
    fun insertAll(articles: List<InputEntity>)

    @Query("DELETE FROM input_table")
    fun deleteAll()
}
