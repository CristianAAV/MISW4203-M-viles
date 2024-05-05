package com.example.mis4203movilvinilosjpc.Navigation

sealed class AppScreem (val route:String) {
    // se necesita crear una ruta para navegar entre pantalla
    //tambien creamos objetos segun la cantidad de pantallas
    object ActivityPrincipal:AppScreem("ActivityPrincipal")
    object DetalleAlbum:AppScreem("DetalleAlbum/{id}") {
        fun createRoute(id:String) = "DetalleAlbum/$id"
    }
    object DetalleArtista:AppScreem("DetalleArtista/{artistaId}") {
        fun createRoute(artistaId:String) = "DetalleArtista/$artistaId"
    }
    object DetalleColeccionista:AppScreem("DetalleColeccionista/{coleccionistaId}"){
        fun createRoute(coleccionistaId:String) = "DetalleColeccionista/$coleccionistaId"
    }
}