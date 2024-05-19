package com.example.mis4203movilvinilosjpc.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.CrearAlbum.AlbumCreate
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.View.DetalleAlbum.DetalleAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.CreateAlbumsViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.View.DetalleArtista.DetalleArtista
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel.ArtistaViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.View.CollectorsDetalle.DetalleCollector
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.CollectorViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.UI.View.ActivityPrincipal


@Composable
fun  NavigationScreem(
    albumsViewModel: AlbumsViewModel,
    artistaViewModel: ArtistaViewModel,
    collectorViewModel: CollectorViewModel,
    createAlbumsViewModel: CreateAlbumsViewModel
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreem.ActivityPrincipal.route){
        composable(route = AppScreem.ActivityPrincipal.route){
            ActivityPrincipal(navController = navController, albumsViewModel,artistaViewModel,collectorViewModel,createAlbumsViewModel)
        }
        composable(route = AppScreem.DetalleAlbum.route){backStackEntry->
            DetalleAlbum(
                navController = navController,
                albumsViewModel,
                backStackEntry.arguments?.getString("id"))
        }
        composable(route = AppScreem.DetalleArtista.route) { backStackEntry ->
            DetalleArtista(
                navController = navController,
                artistaViewModel = artistaViewModel,
                data = backStackEntry.arguments?.getString("artistaId")
            )
        }
        composable(route = AppScreem.DetalleColeccionista.route) { backStackEntry ->
            DetalleCollector(
                navController = navController,
                collectorViewModel = collectorViewModel,
                data = backStackEntry.arguments?.getString("coleccionistaId")
            )
        }
        composable(route = AppScreem.AlbumCreate.route){
            AlbumCreate(createAlbumsViewModel,navController,artistaViewModel)
        }

    }
}