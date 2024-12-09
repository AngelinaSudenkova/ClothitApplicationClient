package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.repository.WardrobeRepository.OutfitRepository
import javax.inject.Inject

class DeleteOutfitUC @Inject constructor(
    private val repository: OutfitRepository
) {
     suspend operator fun invoke(outfitId: Int) = repository.deleteOutfit(outfitId)
}