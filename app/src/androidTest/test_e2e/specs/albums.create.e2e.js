
  import { assert } from 'chai'
  describe("test albums create",() => {
    beforeEach (async () =>{

    },{ shortCircuit: true });

    it('should display the album create field nombre on the screen', async function() {
        const buttonElement = await $('//android.view.View[@content-desc="btnCreateAlbum"]');
        if (!buttonElement) {
            throw new Error('No se encontró el botón "+"');
        }
        await buttonElement.click();
        await browser.pause(1000);
        const lblfieldNombreElement = await $('//android.widget.TextView[@content-desc="lblfieldNombre"]');
        if (!lblfieldNombreElement) {
            throw new Error('No se encontró el elemento del label nombre para el álbum');
        }
        const lblfieldNombreText = await lblfieldNombreElement.getAttribute('text');
        assert.isNotEmpty(lblfieldNombreText, 'Nombre');
        assert.include(lblfieldNombreText, 'Nombre');
        const fieldNombreElement = await $('(//android.view.View[@content-desc="fieldNombre"])');
        if (!fieldNombreElement) {
            throw new Error('No se encontró el elemento del nombre para el álbum');
        }
        await fieldNombreElement.click();
        await browser.pause(1000);
        await browser.keys('Album de musica');
        await browser.pause(2000);
        await driver.back();
        await browser.pause(2000);
        const NombreText = await $('(//android.widget.EditText[@text="Album de musica"])').getAttribute('text');
        assert.isNotEmpty(NombreText, 'Album de musica');
        assert.include(NombreText, 'Album de musica');
      });


    it('should display the album create field Artista on the screen', async function() {
        const lblfieldArtistaElement = await $('//android.widget.TextView[@content-desc="lblfieldArtista"]');
        if (!lblfieldArtistaElement) {
            throw new Error('No se encontró el elemento del label artista para el álbum');
        }
        const lblfieldArtistaText = await lblfieldArtistaElement.getAttribute('text');
        assert.isNotEmpty(lblfieldArtistaText, 'Artista');
        assert.include(lblfieldArtistaText, 'Artista');
        const fieldArtistaElement = await $('//android.view.View[@content-desc="fieldArtista"]');
        if (!fieldArtistaElement) {
            throw new Error('No se encontró el elemento del Artista para el álbum');
        }
        await fieldArtistaElement.click();
        await browser.pause(3000);
        await $('//android.widget.TextView[@text="Rubén Blades Bellido de Luna"]').click();
        const ArtistaText = await $('//android.widget.EditText[@text="Rubén Blades Bellido de Luna"]').getAttribute('text');
        assert.isNotEmpty(ArtistaText, 'Rubén Blades Bellido de Luna');
        assert.include(ArtistaText, 'Rubén Blades Bellido de Luna');
      });

    it('should display the album create field Genero on the screen', async function() {
        const lblfieldGeneroElement = await $('//android.widget.TextView[@content-desc="lblfieldGenero"]');
        if (!lblfieldGeneroElement) {
            throw new Error('No se encontró el elemento del label genero para el álbum');
        }
        const lblfieldGeneroText = await lblfieldGeneroElement.getAttribute('text');
        assert.isNotEmpty(lblfieldGeneroText, 'Genero');
        assert.include(lblfieldGeneroText, 'Genero');
        const fieldGeneroElement = await $('//android.view.View[@content-desc="fieldGenero"]');
        if (!fieldGeneroElement) {
            throw new Error('No se encontró el elemento del Genero para el álbum');
        }
        await fieldGeneroElement.click();
        await browser.pause(3000);
        await $('//android.widget.TextView[@text="Classical"]').click();
        const GeneroText = await $('//android.widget.EditText[@text="Classical"]').getAttribute('text');
        assert.isNotEmpty(GeneroText, 'Classical');
        assert.include(GeneroText, 'Classical');
      });


});