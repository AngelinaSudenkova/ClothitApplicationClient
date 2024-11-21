package com.example.clothitapplication.data.repository.local


import androidx.room.*
import com.example.clothitapplication.data.dto.ItemDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Query("SELECT * FROM items WHERE category = :category")
    fun getItemsByCategory(category: String): Flow<List<ItemDto>>

    @Query("SELECT * FROM items WHERE id = :id")
    suspend fun getItemById(id: Int): ItemDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: ItemDto) : Long

    @Delete
    suspend fun deleteItem(item: ItemDto)

    @Update
    suspend fun updateItem(item: ItemDto)
}