/*const AlbumsScreen = require("../../screenObjects/albums.screen");

describe("Title of first Album", () => {
  beforeEach(async () => {
    await AlbumsScreen.Albums.click();
  });

    it("there is an album", async () => {
      await expect(AlbumsScreen.titleAlbums).toBeTruthy();
    });


  });*/

 /* const assert = require('assert');
  const { driver } = require('./wdio.conf.js'); // Reemplaza './config' con la ruta correcta a tu archivo de configuración de Appium

  describe('Album title validation', function() {
      beforeEach(async function() {
          // Agrega código para navegar a la pantalla de álbumes si es necesario
      });

      it('should validate that album title is not empty', async function() {
          // Espera a que se cargue la pantalla de álbumes si es necesario

          // Encuentra el elemento del título del álbum por su etiqueta de texto
          const albumTitleElement = await driver.$('new UiSelector().text("Título del álbum")');

          // Obtiene el texto del elemento del título del álbum
          const albumTitle = await albumTitleElement.getText();

          // Verifica que el título del álbum esté presente y no esté vacío
          assert.strictEqual(albumTitle.trim(), '', 'El título del álbum está vacío');
      });
  });*/

  /*import { assert } from 'chai';

  describe('Album screen title', function() {
      it('should display album screen title', async function() {
          // Encuentra el elemento del título del álbum por su ID, clase, u otro selector
          const titleElement = await $('~titleAlbum');
          // Obtiene el texto del elemento del título del álbum
          const titleText = await titleElement.getText();
          // Verifica que el título no esté vacío
          assert.isNotEmpty(titleText, 'El título del álbum está vacío');
      });
  });*/

  import { assert } from 'chai'
  describe("album",() => {
    beforeEach (async () =>{
    

    },{ shortCircuit: true });
    it('should display the album title on the screen', async function() {
      const titleElement = await $('//android.widget.TextView[@text="Vinilos"]');
      if (!titleElement) {
          throw new Error('No se encontró el elemento del título del álbum');
      }
      const titleText = await titleElement.getText();
      assert.isNotEmpty(titleText, 'Vinilos');
  });
  });
