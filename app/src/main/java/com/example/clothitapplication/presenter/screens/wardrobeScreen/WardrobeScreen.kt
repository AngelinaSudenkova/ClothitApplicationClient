package com.example.clothitapplication.presenter.screens.wardrobeScreen

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.clothitapplication.R
import com.example.clothitapplication.domain.model.DataOrException
import com.example.clothitapplication.domain.model.wardrobeModel.ItemEntity
import com.example.clothitapplication.domain.model.wardrobeModel.WardrobeCategory
import com.example.clothitapplication.navigation.AuthorizedClothitScreens
import com.example.clothitapplication.navigation.Graph
import com.example.clothitapplication.presenter.components.ItemCardListCard
import com.example.clothitapplication.presenter.components.WardrobeTopBar

@Composable
fun WardrobeScreen(
    navController: NavHostController,
    viewModel: WardrobeViewModel = hiltViewModel<WardrobeViewModel>()
) {
    val accessoriesState by viewModel.accessoriesState
    val topsState by viewModel.topsState
    val bottomsState by viewModel.bottomsState
    val shoesState by viewModel.shoesState

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            WardrobeTopBar(
                onItemAddClick = {
                    navController.navigate(Graph.ITEM) {
                        launchSingleTop = true
                        popUpTo(AuthorizedClothitScreens.ItemScreen.name)
                    }
                },
                onCreateOutfitClick = { /* TODO */ }
            )

            LazyColumn {
                item {
                    when {
                        accessoriesState.loading == true -> {
                            LinearProgressIndicator()
                        }

                        accessoriesState.e != null -> {
                            Text(text = "Failed to load accessories: ${accessoriesState.e!!.message}")
                        }

                        else -> {
                            ItemCardListCard(
                                categoryName = WardrobeCategory.ACCESSORIES.name,
                                imageUrls = accessoriesState.data!!.map { Uri.parse(it.imgUrl) })
                        }
                    }
                }
                item {
                    when {
                        topsState.loading == true -> {
                            LinearProgressIndicator()
                        }

                        topsState.e != null -> {
                            Text(text = "Failed to load tops: ${topsState.e!!.message}")
                        }

                        else -> {
                            ItemCardListCard(
                                categoryName = WardrobeCategory.TOPS.name,
                                imageUrls = topsState.data!!.map { Uri.parse(it.imgUrl) })
                        }
                    }
                }
                item {
                    when {
                        bottomsState.loading == true -> {
                            LinearProgressIndicator()
                        }

                        bottomsState.e != null -> {
                            Text(text = "Failed to load bottoms: ${bottomsState.e!!.message}")
                        }

                        else -> {
                            ItemCardListCard(
                                categoryName = WardrobeCategory.BOTTOMS.name,
                                imageUrls = bottomsState.data!!.map { Uri.parse(it.imgUrl) })
                        }
                    }
                }
                item {
                    when {
                        shoesState.loading == true -> {
                            LinearProgressIndicator()
                        }

                        shoesState.e != null -> {
                            Text(text = "Failed to load shoes: ${shoesState.e!!.message}")
                        }

                        else -> {
                            ItemCardListCard(
                                categoryName = WardrobeCategory.SHOES.name,
                                imageUrls = shoesState.data!!.map { Uri.parse(it.imgUrl) })
                        }
                    }
                }

            }
        }
    }

}


