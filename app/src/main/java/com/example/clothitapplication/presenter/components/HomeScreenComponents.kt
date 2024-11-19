package com.example.clothitapplication.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.example.clothitapplication.R
import androidx.compose.material3.Icon
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.clothitapplication.presenter.ui.theme.LocalCustomColorScheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.HazeMaterials





//@Preview
//@Composable
//fun WardrobeTopBar(
//    title: String = stringResource(R.string.your_wardrobe),
//    onItemAddClick: () -> Unit = {},
//    onCreateOutfitClick: () -> Unit = {}
//) {
//    val expanded = remember { mutableStateOf(false) }
//    val gradientBrush = Brush.radialGradient(
//        colors = listOf(
//            LocalCustomColorScheme.current.purpleAccent.copy(alpha = 0.5f),
//            LocalCustomColorScheme.current.purpleAccent.copy(alpha = 0.0f)
//        ),
//
//        center = with(LocalDensity.current) { Offset(Size.width().toFloat()-32.dp.toPx(), 32.dp.toPx()) },
//        radius = 300f
//    )
//    val hazeState = remember { HazeState() }
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 32.dp, vertical = 32.dp),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = CenterVertically
//    ) {
//        Text(
//            text = title,
//            style = MaterialTheme.typography.headlineLarge,
//            color = MaterialTheme.colorScheme.onBackground
//        )
//        Box(
//            modifier = Modifier
//                .background(brush = gradientBrush)
//                .blur(32.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
//        )
//        Box {
//            Box(
//                modifier = Modifier
//                    .size(48.dp)
//                    .clip(CircleShape)
//                    .background(Color.Transparent)
//                    .hazeChild(state = hazeState, style = HazeMaterials.thin())
//                    .border(
//                        width = 2.dp,
//                        brush = Brush.linearGradient(
//                            listOf(
//                                Color.White.copy(alpha = .8f),
//                                Color.White.copy(alpha = .2f),
//                            )
//                        ),
//                        shape = CircleShape
//                    )
//            ) {
//                IconButton(
//                    onClick = { expanded.value = !expanded.value },
//                    modifier = Modifier
//                        .align(Alignment.Center)
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Add,
//                        contentDescription = stringResource(R.string.add),
//                        tint = MaterialTheme.colorScheme.onBackground
//                    )
//                }
//            }
//        }
//            DropdownMenu(
//                expanded = expanded.value,
//                onDismissRequest = { expanded.value = false },
//                containerColor = MaterialTheme.colorScheme.background
//            ) {
//                DropdownMenuItem(
//                    text = { Text(stringResource(R.string.add_new_item)) },
//                    onClick = {
//                        onItemAddClick()
//                        expanded.value = false
//                    }
//                )
//                DropdownMenuItem(
//                    text = { Text(stringResource(R.string.create_outfit)) },
//                    onClick = {
//                        onCreateOutfitClick()
//                        expanded.value = false
//                    }
//                )
//            }
//        }
//    }

@Composable
fun WardrobeTopBar(
    title: String = stringResource(R.string.your_wardrobe),
    onItemAddClick: () -> Unit = {},
    onCreateOutfitClick: () -> Unit = {}
) {
    val expanded = remember { mutableStateOf(false) }
    val gradientBrush = Brush.radialGradient(
        colors = listOf(
            LocalCustomColorScheme.current.purpleAccent.copy(alpha = 0.5f),
            LocalCustomColorScheme.current.purpleAccent.copy(alpha = 0.0f)
        ),
        center = Offset(0f, 0f),
        radius = 150f
    )
    val hazeState = remember { HazeState() }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp, vertical = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Box(
            modifier = Modifier.size(48.dp)
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(brush = gradientBrush)
                    .blur(32.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            )
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.Transparent)
                    .hazeChild(state = hazeState, style = HazeMaterials.thin())
                    .border(
                        width = 2.dp,
                        brush = Brush.linearGradient(
                            listOf(
                                Color.White.copy(alpha = .8f),
                                Color.White.copy(alpha = .2f),
                            )
                        ),
                        shape = CircleShape
                    )
            ) {
                IconButton(
                    onClick = { expanded.value = !expanded.value },
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.add),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.align(Alignment.TopEnd),
                containerColor = MaterialTheme.colorScheme.background
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.add_new_item)) },
                    onClick = {
                        onItemAddClick()
                        expanded.value = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.create_outfit)) },
                    onClick = {
                        onCreateOutfitClick()
                        expanded.value = false
                    }
                )
            }
        }
    }
}
