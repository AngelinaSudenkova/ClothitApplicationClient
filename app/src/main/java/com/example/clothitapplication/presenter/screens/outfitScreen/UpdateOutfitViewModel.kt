package com.example.clothitapplication.presenter.screens.outfitScreen

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clothitapplication.domain.model.wardrobeModel.OutfitEntity
import com.example.clothitapplication.domain.usecase.wardrobeUC.DeleteOutfitUC
import com.example.clothitapplication.domain.usecase.wardrobeUC.GetOutfitByIdUC
import com.example.clothitapplication.domain.usecase.wardrobeUC.UpdateOutfitUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateOutfitViewModel @Inject constructor(
    private val getOutfitByIdUC: GetOutfitByIdUC,
    private val updateOutfitUC: UpdateOutfitUC,
    private val deleteOutfitUC: DeleteOutfitUC
) : ViewModel() {
    val _outfitEntityState = mutableStateOf<OutfitEntity?>(null)
    val outfitEntity = _outfitEntityState

    fun getOutfitById(outfitId: Int){
        viewModelScope.launch {
            val outfit = getOutfitByIdUC(outfitId)
            _outfitEntityState.value = outfit
        }
    }

    fun updateOutfit(outfit: OutfitEntity){
        viewModelScope.launch {
            updateOutfitUC(outfit)
        }
    }

    fun deleteOutfit(outfit: OutfitEntity){
        viewModelScope.launch {
            deleteOutfitUC(outfit.id)
        }
    }

}