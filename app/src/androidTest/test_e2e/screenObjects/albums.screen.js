class ALbumsScreen {
    get Albums(){
        return $("tag_albums")
    }

    get titleAlbums() {
        return $("~titleAlbum");
    }

    get performersAlbums() {
        return $("~open menu");
    }

    get detalleAlbums() {
        return $("~open menu");
    }

    get albumTitle() {
        return $("~open menu");
    }

    get albumPerformer() {
        return $("~open menu");
    }

    get albumCancion() {
        return $("~open menu");
    }

    get albumGenero() {
        return $("~open menu");
    }

    get albumReleaseDate() {
        return $("~open menu");
    }


}

module.exports = new ALbumsScreen();
