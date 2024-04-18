package com.example.mis4203movilvinilosjpc.Navigation

sealed class AppScreem (val route:String) {
    // se necesita crear una ruta para navegar entre pantalla
    //tambien creamos objetos segun la cantidad de pantallas
    object ActivityPrincipal:AppScreem("ActivityPrincipal")
}