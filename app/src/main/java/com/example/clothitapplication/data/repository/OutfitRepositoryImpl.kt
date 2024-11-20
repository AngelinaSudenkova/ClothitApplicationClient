package com.example.clothitapplication.data.repository


import com.example.clothitapplication.data.dto.OutfitDto
import com.example.clothitapplication.data.repository.local.ItemDao
import com.example.clothitapplication.data.repository.local.OutfitDao
import com.example.clothitapplication.di.IoDispatcher
import com.example.clothitapplication.domain.model.wardrobeModel.OutfitEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import com.example.clothitapplication.domain.repository.WardrobeRepository.OutfitRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OutfitRepositoryImpl @Inject constructor(
    private val outfitDao: OutfitDao,
    private val itemDao: ItemDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : OutfitRepository {
    override suspend fun getOutfits(): Flow<List<WardrobeShortEntity>> {
        return withContext(dispatcher) {
            outfitDao.getOutfits().map { outfits ->
                outfits.map { outfit -> outfit.toWardrobeShortEntity() }
            }
        }
    }

    override suspend fun getOutfitById(id: Int): OutfitEntity {
        return withContext(dispatcher) {
            val outfitDto = outfitDao.getOutfitById(id)
            val itemEntities = outfitDto.itemIds.mapNotNull { itemId ->
                itemDao.getItemById(itemId)?.toItemEntity()
            }
            outfitDto.toOutfitEntity(itemEntities)
        }
    }


    override suspend fun addOutfit(outfit: OutfitEntity) {
        withContext(dispatcher) {
            outfitDao.addOutfit(outfit.toOutfitDto())
        }
    }

    override suspend fun deleteOutfit(outfit: OutfitEntity) {
        withContext(dispatcher) {
            outfitDao.deleteOutfit(outfit.toOutfitDto())
        }
    }

    override suspend fun updateOutfit(outfit: OutfitEntity) {
        withContext(dispatcher) {
            outfitDao.updateOutfit(outfit.toOutfitDto())
        }
    }
}