
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


    it('should display the album create field Record on the screen', async function() {
        const lblfieldRecordElement = await $('//android.widget.TextView[@content-desc="lblfieldRecord"]');
        if (!lblfieldRecordElement) {
            throw new Error('No se encontró el elemento del label record para el álbum');
        }
        const lblfieldRecordText = await lblfieldRecordElement.getAttribute('text');
        assert.isNotEmpty(lblfieldRecordText, 'Etiqueta de registro');
        assert.include(lblfieldRecordText, 'Etiqueta de registro');
        const fieldRecordElement = await $('//android.view.View[@content-desc="fieldRecord"]');
        if (!fieldRecordElement) {
            throw new Error('No se encontró el elemento del Record para el álbum');
        }
        await fieldRecordElement.click();
        await browser.pause(3000);
        await $('//android.widget.TextView[@text="Discos Fuentes"]').click();
        const RecordText = await $('//android.widget.EditText[@text="Discos Fuentes"]').getAttribute('text');
        assert.isNotEmpty(RecordText, 'Discos Fuentes');
        assert.include(RecordText, 'Discos Fuentes');
      });

    it('should display the album create field Record on the screen', async function() {
        const lblfieldReleaseElement = await $('//android.widget.TextView[@content-desc="lblfieldRelease"]');
        if (!lblfieldReleaseElement) {
            throw new Error('No se encontró el elemento del label release date para el álbum');
        }
        const lblfieldReleaseText = await lblfieldReleaseElement.getAttribute('text');
        assert.isNotEmpty(lblfieldReleaseText, 'Fecha de lanzamiento');
        assert.include(lblfieldReleaseText, 'Fecha de lanzamiento');
        const fieldReleaseElement = await $('//android.view.View[@content-desc="fieldRelease"]');
        if (!fieldReleaseElement) {
            throw new Error('No se encontró el elemento del release date para el álbum');
        }
        await fieldReleaseElement.click();
        await browser.pause(3000);
        await $('//android.view.View[@content-desc="07 mayo 2024"]').click();
        await browser.pause(3000);
        await $('//android.widget.Button[@resource-id="android:id/button1"]').click();
        await browser.pause(3000);
        const RecordText = await $('//android.widget.EditText[@text="2024-5-7"]').getAttribute('text');
        assert.isNotEmpty(RecordText, '2024-5-7');
        assert.include(RecordText, '2024-5-7');
      });

      it('should display the album create field descripcion on the screen', async function() {
        const lblfieldDescripciónElement = await $('//android.widget.TextView[@content-desc="lblfieldDescripción"]');
        if (!lblfieldDescripciónElement) {
            throw new Error('No se encontró el elemento del label descripcion para el álbum');
        }
        const lblfieldDescripciónText = await lblfieldDescripciónElement.getAttribute('text');
        assert.isNotEmpty(lblfieldDescripciónText, 'descripción');
        assert.include(lblfieldDescripciónText, 'descripción');
        const fieldDescripciónElement = await $('(//android.view.View[@content-desc="fieldDescripción"])');
        if (!fieldDescripciónElement) {
            throw new Error('No se encontró el elemento del descripcion para el álbum');
        }
        await fieldDescripciónElement.click();
        await browser.pause(1000);
        await browser.keys('Album numero 1');
        await browser.pause(2000);
        await driver.back();
        await browser.pause(2000);
        const NombreText = await $('(//android.widget.EditText[@text="Album numero 1"])').getAttribute('text');
        assert.isNotEmpty(NombreText, 'Album numero 1');
        assert.include(NombreText, 'Album numero 1');
      });

      it('should display the album Button create y create Album on the screen', async function() {
        const lblfieldCreateElement = await $('//android.widget.TextView[@content-desc="txtCrearAlbum"]');
        if (!lblfieldCreateElement) {
            throw new Error('No se encontró el elemento del text Crear Album para el álbum');
        }
        const lblfieldCreateText = await lblfieldCreateElement.getAttribute('text');
        assert.isNotEmpty(lblfieldCreateText, 'Crear album');
        assert.include(lblfieldCreateText, 'Crear album');
        const btnCrearAlbumElement = await $('(//android.view.View[@content-desc="btnCrearAlbum"])');
        if (!btnCrearAlbumElement) {
            throw new Error('No se encontró el elemento del boton crear para el álbum');
        }
        await btnCrearAlbumElement.click();
        await browser.pause(2000);

        const titleAlbumElement = await $('//android.widget.TextView[@content-desc="albumTitle" and @text="Album de musica"]');
        if (!titleAlbumElement) {
            throw new Error('No se encontró el elemento del titulo del Album nuevo');
        }
        const textTitleAlbumElement = await titleAlbumElement.getAttribute('text');
        assert.isNotEmpty(textTitleAlbumElement, 'Album de musica');
        assert.include(textTitleAlbumElement, 'Album de musica');
    });


});