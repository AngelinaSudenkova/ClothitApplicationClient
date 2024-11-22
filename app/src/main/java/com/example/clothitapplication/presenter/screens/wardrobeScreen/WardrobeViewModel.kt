package com.example.clothitapplication.presenter.screens.wardrobeScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clothitapplication.domain.model.DataOrException
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import com.example.clothitapplication.domain.usecase.wardrobeUC.GetAccessoriesUC
import com.example.clothitapplication.domain.usecase.wardrobeUC.GetBottomsUC
import com.example.clothitapplication.domain.usecase.wardrobeUC.GetOutfitsUC
import com.example.clothitapplication.domain.usecase.wardrobeUC.GetShoesUC
import com.example.clothitapplication.domain.usecase.wardrobeUC.GetTopsUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WardrobeViewModel @Inject constructor(
    private val getOutfitsUC: GetOutfitsUC,
    private val getAccessoriesUC: GetAccessoriesUC,
    private val getTopsUC: GetTopsUC,
    private val getBottomsUC: GetBottomsUC,
    private val getShoesUC: GetShoesUC
) : ViewModel() {


    private val _outfitState: MutableState<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> =
        mutableStateOf(DataOrException(emptyList(), false, null))
    val outfitsState: MutableState<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> =
        _outfitState


    private val _accessoriesState: MutableState<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> =
        mutableStateOf(DataOrException(emptyList(), false, null))
    val accessoriesState: MutableState<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> =
        _accessoriesState

    private val _topsState: MutableState<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> =
        mutableStateOf(DataOrException(emptyList(), false, null))
    val topsState: MutableState<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> =
        _topsState

    private val _bottomsState: MutableState<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> =
        mutableStateOf(DataOrException(emptyList(), false, null))
    val bottomsState: MutableState<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> =
        _bottomsState

    private val _shoesState: MutableState<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> =
        mutableStateOf(DataOrException(emptyList(), false, null))
    val shoesState: MutableState<DataOrException<List<WardrobeShortEntity>, Boolean, Exception>> =
        _shoesState

    init {
        getAllWardrobeItems()
    }

    private fun getAllWardrobeItems() {
        getOutfits()
        getAccessories()
        getTops()
        getBottoms()
        getShoes()
    }

    private fun getOutfits() {
        viewModelScope.launch {
            getOutfitsUC().collect { dataOrException ->
                _outfitState.value = dataOrException
            }
        }
    }

    fun getAccessories() = viewModelScope.launch {
        getAccessoriesUC().collect { dataOrException ->
            _accessoriesState.value = dataOrException
        }
    }

    fun getTops() = viewModelScope.launch {
        getTopsUC().collect { dataOrException ->
            _topsState.value = dataOrException
        }
    }

    fun getBottoms() = viewModelScope.launch {
        getBottomsUC().collect { dataOrException ->
            _bottomsState.value = dataOrException
        }
    }

    fun getShoes() = viewModelScope.launch {
        getShoesUC().collect { dataOrException ->
            _shoesState.value = dataOrException
        }
    }
}