package com.example.clothitapplication.presenter.screens.outfitScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.clothitapplication.R
import com.example.clothitapplication.navigation.AuthorizedClothitScreens
import com.example.clothitapplication.presenter.components.DeleteButton
import com.example.clothitapplication.presenter.components.EditButton
import com.example.clothitapplication.presenter.components.OutfitCreationForm
import com.example.clothitapplication.presenter.components.OutfitItemsRow
import com.example.clothitapplication.presenter.components.OutfitScreenTopBar
import com.example.clothitapplication.presenter.components.SendButton

@Composable
fun UpdateOutfitScreen(
    navController: NavController,
    outfitId: Int,
    outfitViewModel: UpdateOutfitViewModel = hiltViewModel<UpdateOutfitViewModel>()
) {

    LaunchedEffect(Unit) {
        outfitViewModel.getOutfitById(outfitId)
    }
    val outfitEntity by outfitViewModel.outfitEntity

    val outfitName = remember(outfitEntity) { outfitEntity?.name }
    val outfitSeason = remember(outfitEntity) { outfitEntity?.season }
    val outfitDescription = remember(outfitEntity) { outfitEntity?.description }
    val outfitEntities = remember(outfitEntity) { outfitEntity?.items }


    if (outfitEntity != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            OutfitScreenTopBar(
                title = outfitName ?: stringResource(R.string.outfit),
            ) { navController.popBackStack() }
            OutfitCreationForm(
                oldCategory = outfitSeason.toString(),
                oldDescription = outfitDescription ?: "",
                isUpdateScreen = true
            )
            OutfitItemsRow(
                itemList = outfitEntities ?: emptyList(),
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                EditButton() {
                    navController.navigate(AuthorizedClothitScreens.CreateOutfitScreen.name + "?/$outfitId")
                }
                DeleteButton {
                    outfitViewModel.deleteOutfit(outfitEntity!!)
                    navController.popBackStack()
                }
                SendButton() {
                    //navigate to List of friends list
                }
            }
        }
    }
}

