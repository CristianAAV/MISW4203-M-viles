package com.example.mis4203movilvinilosjpc.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.DetalleAlbum.DetalleAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.UI.View.ActivityPrincipal


@Composable
fun  NavigationScreem(
    albumsViewModel: AlbumsViewModel
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreem.ActivityPrincipal.route){
        composable(route = AppScreem.ActivityPrincipal.route){
            ActivityPrincipal(navController = navController, albumsViewModel)
        }
        composable(route = AppScreem.DetalleAlbum.route){backStackEntry->

            DetalleAlbum(navController = navController, albumsViewModel, backStackEntry.arguments?.getString("id"))
        }


    }
}