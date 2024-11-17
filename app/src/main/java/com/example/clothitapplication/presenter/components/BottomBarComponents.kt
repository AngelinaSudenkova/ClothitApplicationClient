package com.example.clothitapplication.presenter.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.HazeMaterials


//@Composable
//fun BottomBarTabs(
//    tabs: List<BottomBarTab>,
//    selectedTab: Int,
//    onTabSelected: (BottomBarTab) -> Unit,
//) {
//    CompositionLocalProvider(
//        LocalTextStyle provides LocalTextStyle.current.copy(
//            fontSize = 12.sp,
//            fontWeight = FontWeight.Medium,
//        ),
//        LocalContentColor provides Color.White
//    ) {
//        Row(
//            modifier = Modifier.fillMaxSize(),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            tabs.forEach { tab ->
//                val tabIndex = tabs.indexOf(tab)
//                val alpha by animateFloatAsState(
//                    targetValue = if (selectedTab == tabIndex) 1f else .35f,
//                    label = "alpha"
//                )
//                val scale by animateFloatAsState(
//                    targetValue = if (selectedTab == tabIndex) 1f else .98f,
//                    visibilityThreshold = .000001f,
//                    animationSpec = spring(
//                        stiffness = Spring.StiffnessLow,
//                        dampingRatio = Spring.DampingRatioMediumBouncy,
//                    ),
//                    label = "scale"
//                )
//                TabItem(tab, alpha, scale, onTabSelected)
//            }
//        }
//    }
//}
//
//@Composable
//private fun TabItem(
//    tab: BottomBarTab,
//    alpha: Float,
//    scale: Float,
//    onTabSelected: (BottomBarTab) -> Unit
//) {
//    Column(
//        modifier = Modifier
//            .scale(scale)
//            .alpha(alpha)
//            .fillMaxHeight()
//            .pointerInput(Unit) {
//                detectTapGestures {
//                    onTabSelected(tab)
//                }
//            },
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//    ) {
//        Icon(imageVector = tab.icon, contentDescription = "tab ${tab.title}")
//        Text(text = tab.title)
//    }
//}
//
//
//@Composable
//fun BottomNavigationBarCustom(
//    hazeState: HazeState,
//    navController: NavHostController,
//) {
//    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
//
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.BottomCenter
//    ) {
//        BottomNavigationBarBackground(hazeState)
//        Box(
//            modifier = Modifier
//                .padding(horizontal = 24.dp, vertical = 64.dp)
//                .fillMaxWidth()
//                .height(80.dp)
//        ) {
//            BottomBarTabs(
//                tabs = tabs,
//                selectedTab = selectedTabIndex,
//                onTabSelected = { tab ->
//                    val newTabIndex = tabs.indexOf(tab)
//                    if (newTabIndex != selectedTabIndex) {
//                        selectedTabIndex = newTabIndex
//
//                        val currentRoute = navController.currentBackStackEntry?.destination?.route
//                        val newRoute = tab.getRoute(tab)
//
//                        if (currentRoute != newRoute) {
//                            navController.navigate(newRoute) {
//                                launchSingleTop = true
//                                restoreState = true
//                                popUpTo(navController.graph.startDestinationId) {
//                                    saveState = true
//                                }
//                            }
//                        }
//                    }
//                }
//            )
//            AnimatedCircleIndicator(selectedTabIndex)
//        }
//    }
//}
//
//@Composable
//private fun BottomNavigationBarBackground(hazeState: HazeState) {
//    Box(
//        modifier = Modifier
//            .padding(horizontal = 24.dp, vertical = 64.dp)
//            .fillMaxWidth()
//            .height(80.dp)
//            .clip(
//                RoundedCornerShape(
//                    topStart = 64.dp,
//                    topEnd = 64.dp,
//                    bottomStart = 64.dp,
//                    bottomEnd = 64.dp
//                )
//            )
//            .background(Color.Gray)
//            .hazeChild(state = hazeState, style = HazeMaterials.thin())
//            .border(
//                width = Dp.Hairline,
//                brush = Brush.verticalGradient(
//                    colors = listOf(
//                        Color.White.copy(alpha = .8f),
//                        Color.White.copy(alpha = .2f),
//                    )
//                ),
//                shape = RoundedCornerShape(
//                    topStart = 64.dp,
//                    topEnd = 64.dp,
//                    bottomStart = 64.dp,
//                    bottomEnd = 64.dp
//                )
//            )
//    )
//}
//
//@Composable
//private fun AnimatedCircleIndicator(selectedTabIndex: Int) {
//    val animatedSelectedTabIndex by animateFloatAsState(
//        targetValue = selectedTabIndex.toFloat(),
//        label = "animatedSelectedTabIndex",
//        animationSpec = spring(
//            stiffness = Spring.StiffnessLow,
//            dampingRatio = Spring.DampingRatioLowBouncy,
//        )
//    )
//
//    val animatedColor by animateColorAsState(
//        targetValue = tabs[selectedTabIndex].color,
//        label = "animatedColor",
//        animationSpec = spring(
//            stiffness = Spring.StiffnessLow,
//        )
//    )
//
//    Canvas(
//        modifier = Modifier
//            .fillMaxSize()
//            .clip(CircleShape)
//            .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
//    ) {
//        val tabWidth = size.width / tabs.size
//        drawCircle(
//            color = animatedColor.copy(alpha = .6f),
//            radius = size.height / 2,
//            center = Offset(
//                (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
//                size.height / 2
//            )
//        )
//    }
//
//    DrawPathIndicator(animatedSelectedTabIndex, animatedColor)
//}
//
//@Composable
//private fun DrawPathIndicator(animatedSelectedTabIndex: Float, animatedColor: Color) {
//    Canvas(
//        modifier = Modifier
//            .fillMaxSize()
//            .clip(CircleShape)
//    ) {
//        val path = Path().apply {
//            addRoundRect(RoundRect(size.toRect(), CornerRadius(size.height)))
//        }
//        val length = PathMeasure().apply { setPath(path, false) }.length
//
//        val tabWidth = size.width / tabs.size
//        drawPath(
//            path,
//            brush = Brush.horizontalGradient(
//                colors = listOf(
//                    animatedColor.copy(alpha = 0f),
//                    animatedColor.copy(alpha = 1f),
//                    animatedColor.copy(alpha = 1f),
//                    animatedColor.copy(alpha = 0f),
//                ),
//                startX = tabWidth * animatedSelectedTabIndex,
//                endX = tabWidth * (animatedSelectedTabIndex + 1),
//            ),
//            style = Stroke(
//                width = 6f,
//                pathEffect = PathEffect.dashPathEffect(
//                    intervals = floatArrayOf(length / 2, length)
//                )
//            )
//        )
//    }
//}


@Composable
fun BottomBarTabs(
    tabs: List<BottomBarTab>,
    selectedTab: Int,
    onTabSelected: (BottomBarTab) -> Unit,
) {
    CompositionLocalProvider(
        LocalTextStyle provides LocalTextStyle.current.copy(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        ),
        LocalContentColor provides Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            for (tab in tabs) {
                val alpha by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .35f,
                    label = "alpha"
                )
                val scale by animateFloatAsState(
                    targetValue = if (selectedTab == tabs.indexOf(tab)) 1f else .98f,
                    visibilityThreshold = .000001f,
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow,
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                    ),
                    label = "scale"
                )
                Column(
                    modifier = Modifier
                        .scale(scale)
                        .alpha(alpha)
                        .fillMaxHeight()
                        .weight(1f)
                        .pointerInput(Unit) {
                            detectTapGestures {
                                onTabSelected(tab)
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(imageVector = tab.icon, contentDescription = "tab ${tab.title}")
                    Text(text = tab.title)
                }
            }
        }
    }
}


@Composable
fun BottomNavigationBarCustom(
    hazeState: HazeState,
    navController: NavHostController,
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    LaunchedEffect(currentBackStackEntry) {
        currentBackStackEntry?.destination?.route?.let { route ->
            val newIndex = tabs.indexOfFirst { it.getRoute(it) == route }
            if (newIndex != -1) {
                selectedTabIndex = newIndex
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 64.dp)
                .fillMaxWidth()
                .height(80.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 64.dp,
                        topEnd = 64.dp,
                        bottomStart = 64.dp,
                        bottomEnd = 64.dp
                    )
                )
                .background(Color.Transparent)
                .hazeChild(state = hazeState, style = HazeMaterials.thin())
                .border(
                    width = Dp.Hairline,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = .8f),
                            Color.White.copy(alpha = .2f),
                        )
                    ),
                    shape = RoundedCornerShape(
                        topStart = 64.dp,
                        topEnd = 64.dp,
                        bottomStart = 64.dp,
                        bottomEnd = 64.dp
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            BottomBarTabs(
                tabs = tabs,
                selectedTab = selectedTabIndex,
                onTabSelected = {
                    selectedTabIndex = tabs.indexOf(it)
                    navController.navigate(it.getRoute(it)) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )

            val animatedSelectedTabIndex by animateFloatAsState(
                targetValue = selectedTabIndex.toFloat(),
                label = "animatedSelectedTabIndex",
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                    dampingRatio = Spring.DampingRatioLowBouncy,
                )
            )

            val animatedColor by animateColorAsState(
                targetValue = tabs[selectedTabIndex].color,
                label = "animatedColor",
                animationSpec = spring(
                    stiffness = Spring.StiffnessLow,
                )
            )
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .blur(50.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            ) {
                val tabWidth = size.width / tabs.size
                drawCircle(
                    color = animatedColor.copy(alpha = .6f),
                    radius = size.height / 2,
                    center = Offset(
                        (tabWidth * animatedSelectedTabIndex) + tabWidth / 2,
                        size.height / 2
                    )
                )
            }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            ) {
                val path = Path().apply {
                    addRoundRect(RoundRect(size.toRect(), CornerRadius(size.height)))
                }
                val length = PathMeasure().apply { setPath(path, false) }.length

                val tabWidth = size.width / tabs.size
                drawPath(
                    path,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            animatedColor.copy(alpha = 0f),
                            animatedColor.copy(alpha = 1f),
                            animatedColor.copy(alpha = 1f),
                            animatedColor.copy(alpha = 0f),
                        ),
                        startX = tabWidth * animatedSelectedTabIndex,
                        endX = tabWidth * (animatedSelectedTabIndex + 1),
                    ),
                    style = Stroke(
                        width = 6f,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(length / 2, length)
                        )
                    )
                )
            }

        }
    }
}