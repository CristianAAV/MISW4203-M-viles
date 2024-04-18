package com.example.mis4203movilvinilosjpc.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.UI.View.ActivityPrincipal


@Composable
fun  NavigationScreem (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreem.ActivityPrincipal.route){
        composable(route = AppScreem.ActivityPrincipal.route){
            ActivityPrincipal(navController = navController)
        }
    }
}