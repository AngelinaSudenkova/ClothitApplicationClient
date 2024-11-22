package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.model.wardrobeModel.OutfitEntity
import com.example.clothitapplication.domain.repository.WardrobeRepository.OutfitRepository
import javax.inject.Inject

class CreateOutfitUC @Inject constructor(
    private val outfitRepository: OutfitRepository
) {
    suspend operator fun invoke(outfit: OutfitEntity) {
        outfitRepository.addOutfit(outfit = outfit)
    }
}