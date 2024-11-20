package com.example.clothitapplication.domain.repository.WardrobeRepository

import com.example.clothitapplication.domain.model.wardrobeModel.OutfitEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import kotlinx.coroutines.flow.Flow

interface OutfitRepository {
    suspend fun getOutfits(): Flow<List<WardrobeShortEntity>>
    suspend fun getOutfitById(id: Int): OutfitEntity
    suspend fun addOutfit(outfit: OutfitEntity)
    suspend fun deleteOutfit(outfit: OutfitEntity)
    suspend fun updateOutfit(outfit: OutfitEntity)
}