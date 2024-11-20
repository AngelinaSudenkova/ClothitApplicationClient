package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeCategory
import com.example.clothitapplication.domain.repository.WardrobeRepository.ItemRepository
import com.example.clothitapplication.domain.repository.WardrobeRepository.OutfitRepository
import javax.inject.Inject

class GetOutfitsUC @Inject constructor(
    private val repository: OutfitRepository
) {
    suspend operator fun invoke() = repository.getOutfits()
}
