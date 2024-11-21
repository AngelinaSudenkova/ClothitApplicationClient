package com.example.clothitapplication.domain.usecase.wardrobeUC

import com.example.clothitapplication.domain.model.DataOrException
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import com.example.clothitapplication.domain.repository.WardrobeRepository.ItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoesUC @Inject constructor(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(): Flow<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> {
        return repository.getItemsByCategory("SHOES")
    }
}