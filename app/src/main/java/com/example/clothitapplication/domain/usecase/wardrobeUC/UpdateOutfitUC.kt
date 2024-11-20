package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.model.wardrobeModel.OutfitEntity
import com.example.clothitapplication.domain.repository.WardrobeRepository.OutfitRepository
import javax.inject.Inject

class UpdateOutfitUC @Inject constructor(
    private val repository: OutfitRepository
) {
    suspend fun invoke(outfitEntity: OutfitEntity) = repository.updateOutfit(outfitEntity)
}