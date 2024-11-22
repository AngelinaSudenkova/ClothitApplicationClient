package com.example.clothitapplication.presenter.screens.outfitScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothitapplication.domain.model.wardrobeModel.OutfitEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import com.example.clothitapplication.domain.usecase.wardrobeUC.CreateOutfitUC
import com.example.clothitapplication.domain.usecase.wardrobeUC.GetItemByIdUC
import com.example.clothitapplication.utils.EnumConverters
import com.example.clothitapplication.utils.TimeUtilsCustom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateOutfitViewModel @Inject constructor(
    private val createOutfitUseCase: CreateOutfitUC,
    private val getItemByIdUC: GetItemByIdUC
) : ViewModel() {

    private val _selectedItemsState = mutableStateOf<List<Int>>(emptyList())
    val selectedItems: MutableState<List<Int>> = _selectedItemsState

    private val _selectedItemsEntity = mutableStateOf<List<WardrobeShortEntity>>(emptyList())
    val selectedItemsEntity: MutableState<List<WardrobeShortEntity>> = _selectedItemsEntity

    fun addItemId(id: Int) {
        if (!_selectedItemsState.value.contains(id)) {
            _selectedItemsState.value = _selectedItemsState.value + id
            updateSelectedItemsEntity()
        }
    }

    fun addItemsIds(ids: List<Int>) {
        _selectedItemsState.value = (_selectedItemsState.value + ids).distinct()
        updateSelectedItemsEntity()
    }

    fun removeItemId(id: Int) {
        _selectedItemsState.value = _selectedItemsState.value - id
        updateSelectedItemsEntity()
    }

    private fun updateSelectedItemsEntity() {
        viewModelScope.launch {
            val itemList = _selectedItemsState.value.map { id ->
                getItemByIdUC(id)!!.toWardrobeShortEntity()
            }
            _selectedItemsEntity.value = itemList
        }
    }

    fun createOutfit(
        outfitName : String,
        outfitSeason: String,
        outfitDescription: String
    ){
        viewModelScope.launch {
            val itemList = selectedItems.value.map { index ->
                getItemByIdUC.invoke(index)
            }
                createOutfitUseCase.invoke(
                    OutfitEntity(
                        name = outfitName,
                        season = EnumConverters.fromStringToSeason(outfitSeason),
                        description = outfitDescription,
                        items = itemList,
                        timeCreation = TimeUtilsCustom.getCurrentTime(),
                        timeEdition = TimeUtilsCustom.getCurrentTime(),
                        imgUrl = itemList[0]!!.imgUrl
                    )
                )
        }
        selectedItems.value = emptyList()
        selectedItemsEntity.value = emptyList()
    }
}