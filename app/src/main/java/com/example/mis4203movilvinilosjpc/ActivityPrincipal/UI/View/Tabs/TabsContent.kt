package com.example.mis4203movilvinilosjpc.ActivityPrincipal.UI.View.Tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Data.Models.tabsItems
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.UI.ViewModel.ActivityPrincipalViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(navController: NavController) {
    val tabs = listOf(
        tabsItems.Album,
        tabsItems.Artista,
        tabsItems.Coleccionista
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        // provide pageCount
        tabs.size}
    Column {
        Tabs(pagerState = pagerState, tabs = tabs)
        Contents(pagerState = pagerState, tabs = tabs,navController = navController)
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Contents(pagerState: PagerState, tabs: List<tabsItems>,navController: NavController) {
    HorizontalPager(
        state = pagerState,
    )
    {page ->
        tabs[page].screem(navController)

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState: PagerState, tabs: List<tabsItems>) {
    val scope = rememberCoroutineScope() // corrutina para cargar las imagenes de cada tabs
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                   scope.launch {
                       pagerState.animateScrollToPage(index)

                   }

                },
                text = {
                    Text(text = tab.title,
                        fontSize = 15.sp)
                }
            )
        }
    }
}
