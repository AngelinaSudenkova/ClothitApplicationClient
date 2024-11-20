package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.model.wardrobeModel.OutfitEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeCategory
import com.example.clothitapplication.domain.repository.WardrobeRepository.OutfitRepository
import javax.inject.Inject

class AddOutfitUC @Inject constructor(
    private val repository: OutfitRepository
) {
    suspend operator fun invoke(outfitEntity: OutfitEntity) = repository.addOutfit(outfitEntity)
}