package com.example.clothitapplication.presenter.screens.outfitScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeShortEntity
import com.example.clothitapplication.navigation.AuthorizedClothitScreens
import com.example.clothitapplication.navigation.Graph
import com.example.clothitapplication.presenter.components.AddItemCategoryRow
import com.example.clothitapplication.presenter.components.BackButton
import com.example.clothitapplication.presenter.components.CreateOutfitScreenHeader
import com.example.clothitapplication.presenter.components.NextButton
import com.example.clothitapplication.presenter.components.OutfitCreationForm
import com.example.clothitapplication.presenter.components.SelectButton
import com.example.clothitapplication.presenter.components.SummaryForm
import com.example.clothitapplication.utils.EnumConverters

@Composable
fun CreateOutfitScreen(
    navController: NavController,
    outfitViewModel: CreateOutfitViewModel
) {
    val outfitName = remember { mutableStateOf("") }
    val outfitSeason = remember { mutableStateOf("") }
    val selectedItems = outfitViewModel.selectedItemsEntity
    val outfitDescription = remember { mutableStateOf("") }

    val steps = listOf("Add Items", "Details", "Summary")
    var currentStep by remember { mutableIntStateOf(0) }


    when (currentStep) {
        0 -> AddItemsStep(
            navController = navController,
            selectedItemsEntity = selectedItems,
            onItemClicked = { outfitViewModel.removeItemId(it) },
            onNext = { currentStep++ })

        1 -> OutfitDetailsStep(onNext = { currentStep++ },
            onBack = { currentStep-- },
            outfitName = outfitName.value,
            onNameChange = { outfitName.value = it },
            outfitSeason = outfitSeason.value,
            onSeasonChange = { outfitSeason.value = it },
            outfitDescription = outfitDescription.value,
            onDescriptionChange = { outfitDescription.value = it })

        2 -> SummaryStep(
            name = outfitName.value,
            season = outfitSeason.value,
            description = outfitDescription.value,
            items = selectedItems.value,
            onBack = { currentStep-- },
            onDone = {
                outfitViewModel.createOutfit(
                    outfitName = outfitName.value,
                    outfitSeason = outfitSeason.value,
                    outfitDescription = outfitDescription.value
                )
                navController.navigate(Graph.HOME) {
                    popUpTo(0)
                }
            })
    }
}


@Composable
fun AddItemsStep(
    navController: NavController,
    onNext: () -> Unit,
    onItemClicked: (Int) -> Unit = {},
    selectedItemsEntity: MutableState<List<WardrobeShortEntity>>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        CreateOutfitScreenHeader {
            navController.navigate(Graph.HOME) {
                popUpTo(0)
            }
        }
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
        ) {
            AddItemCategoryRow(
                category = "Add Accessories",
                selectedItems = selectedItemsEntity.value.filter {
                    EnumConverters.fromWardrobeCategoryToString(it.category) == "ACCESSORIES"
                },
                onNavigateToAddItems = { navController.navigate(AuthorizedClothitScreens.AddItemsToOutfitScreen.name + "/ACCESSORIES") },
                onRemoveItemClicked = { onItemClicked(it) }
            )
            AddItemCategoryRow(
                category = "Add Tops",
                selectedItems = selectedItemsEntity.value.filter {
                    EnumConverters.fromWardrobeCategoryToString(it.category) == "TOPS"
                },
                onNavigateToAddItems = { navController.navigate(AuthorizedClothitScreens.AddItemsToOutfitScreen.name + "/TOPS") },
                onRemoveItemClicked = { onItemClicked(it) }
            )
            AddItemCategoryRow(
                category = "Add Bottoms",
                selectedItems = selectedItemsEntity.value.filter {
                    EnumConverters.fromWardrobeCategoryToString(it.category) == "BOTTOMS"
                },
                onNavigateToAddItems = { navController.navigate(AuthorizedClothitScreens.AddItemsToOutfitScreen.name + "/BOTTOMS") },
                onRemoveItemClicked = { onItemClicked(it) }
            )
            AddItemCategoryRow(
                category = "Add Shoes",
                selectedItems = selectedItemsEntity.value.filter {
                    EnumConverters.fromWardrobeCategoryToString(it.category) == "SHOES"
                },
                onNavigateToAddItems = { navController.navigate(AuthorizedClothitScreens.AddItemsToOutfitScreen.name + "/SHOES") },
                onRemoveItemClicked = { onItemClicked(it) }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NextButton(onNextButtonClicked = { onNext() })
        }
    }
}

@Composable
fun OutfitDetailsStep(
    onNext: () -> Unit,
    onBack: () -> Unit,
    outfitName: String,
    onNameChange: (String) -> Unit,
    outfitSeason: String,
    onSeasonChange: (String) -> Unit,
    outfitDescription: String,
    onDescriptionChange: (String) -> Unit
) {
    Column {
        OutfitCreationForm(
            name = outfitName,
            onNameChange = onNameChange,
            onCategorySelected = onSeasonChange,
            oldCategory = outfitSeason,
            oldDescription = outfitDescription,
            onDescriptionChange = onDescriptionChange
        )
    }
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            BackButton(onBackButtonClicked = onBack)
            NextButton(onNextButtonClicked = onNext)
        }
    }
}

@Composable
fun SummaryStep(
    onBack: () -> Unit,
    name: String,
    season: String,
    description: String,
    items: List<WardrobeShortEntity>,
    onDone: () -> Unit
) {
    Column {
        SummaryForm(
            name = name,
            season = season,
            description = description,
            selectedItems = items
        )
        SelectButton(
            onButtonClick = onDone
        )
    }

}