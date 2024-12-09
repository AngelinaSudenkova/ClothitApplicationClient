package com.example.clothitapplication.domain.repository.WardrobeRepository

import com.example.clothitapplication.domain.model.DataOrException
import com.example.clothitapplication.domain.model.wardrobeModel.OutfitEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import kotlinx.coroutines.flow.Flow

interface OutfitRepository {
    suspend fun getOutfits(): Flow<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>>
    suspend fun getOutfitById(id: Int): OutfitEntity
    suspend fun addOutfit(outfit: OutfitEntity) : Int
    suspend fun deleteOutfit(outfitId: Int)
    suspend fun updateOutfit(outfit: OutfitEntity)
}