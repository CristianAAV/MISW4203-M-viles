package com.example.mis4203movilvinilosjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.UI.ViewModel.AlbumsViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.UI.ViewModel.ArtistaViewModel
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.UI.ViewModel.CollectorViewModel
import com.example.mis4203movilvinilosjpc.Navigation.NavigationScreem
import com.example.mis4203movilvinilosjpc.ui.theme.MIS4203MOVILVINILOSJPCTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   /* @Inject
    lateinit var albumsViewModel: AlbumsViewModel*/
    private lateinit var albumsViewModel: AlbumsViewModel
    private lateinit var artistaViewModel: ArtistaViewModel
    private lateinit var collectorViewModel: CollectorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumsViewModel = ViewModelProvider(this).get(AlbumsViewModel::class.java)
        artistaViewModel = ViewModelProvider(this).get(ArtistaViewModel::class.java)
        collectorViewModel = ViewModelProvider(this).get(CollectorViewModel::class.java)
        setContent {
            MIS4203MOVILVINILOSJPCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationScreem(albumsViewModel,artistaViewModel,collectorViewModel)
                }
            }
        }
    }
}

