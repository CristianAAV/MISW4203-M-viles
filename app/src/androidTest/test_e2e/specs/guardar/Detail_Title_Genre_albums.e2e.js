const AlbumsScreen = require("../../screenObjects/albums.screen");

describe("Title of first Album", () => {
  beforeEach(async () => {
    await AlbumsScreen.Albums.click();
    const detalleAlbum = await AlbumsScreen.detalleAlbums;
    const firstDetalleAlbum = detallealbum[0];

    //const firstDetalleAlbum = await AlbumsScreen.detalleAlbums.first();
    await firstDetalleAlbum.click();
  });

    it("there is an album", async () => {
      await expect(AlbumsScreen.titleAlbums).toBeTruthy();
    });

    it("verifies if the album has the title 'Buscando América'", async () => {
      const albumTitle = await AlbumsScreen.albumTitle;
      await expect(albumTitle).toBe("Buscando América");
    });

    it("verifies if the album has the genre 'Salsa'", async () => {
      const albumGenre = await AlbumsScreen.albumGenero;
      await expect(albumGenre).toBe("Salsa");
    });

  });
