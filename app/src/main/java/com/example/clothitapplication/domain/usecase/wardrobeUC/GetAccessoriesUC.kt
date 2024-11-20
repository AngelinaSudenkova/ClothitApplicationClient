package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.repository.WardrobeRepository.ItemRepository
import javax.inject.Inject

class GetAccessoriesUC @Inject constructor(
    private val repository: ItemRepository
) {
    suspend operator fun invoke() = repository.getItemsByCategory("ACCESSORIES")
}