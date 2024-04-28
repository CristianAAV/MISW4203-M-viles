const AlbumsScreen = require("../../screenObjects/albums.screen");

describe("Title of first Album", () => {
  beforeEach(async () => {
    await AlbumsScreen.Albums.click();
  });

    it("there is an album", async () => {
      await expect(AlbumsScreen.titleAlbums).toBeTruthy();
    });

//    it("verifies if the first album has the title 'Buscando América'", async () => {
//      const albumTitles = await AlbumsScreen.titleAlbums;
//      const firstAlbumTitle = albumTitles[0];
//      await expect(firstAlbumTitle).toBe("Buscando América");
//    });

  });
