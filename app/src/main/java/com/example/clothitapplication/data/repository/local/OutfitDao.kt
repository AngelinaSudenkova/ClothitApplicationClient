package com.example.clothitapplication.data.repository.local

import androidx.room.*
import com.example.clothitapplication.data.dto.OutfitDto
import kotlinx.coroutines.flow.Flow

@Dao
interface OutfitDao {
    @Query("SELECT * FROM outfits")
    fun getOutfits(): Flow<List<OutfitDto>>

    @Query("SELECT * FROM outfits WHERE id = :id")
    suspend fun getOutfitById(id: Int): OutfitDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOutfit(outfit: OutfitDto) : Long

    @Delete
    suspend fun deleteOutfit(outfit: OutfitDto)

    @Update
    suspend fun updateOutfit(outfit: OutfitDto)
}