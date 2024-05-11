
  import { assert } from 'chai'
  describe("test detail Artist",() => {
    beforeEach (async () =>{


    },{ shortCircuit: true });


      it('should navigate to details activity when "Detalles" button is clicked', async function() {

        await $('//android.widget.TextView[@text="Artistas"]').click();
        await browser.pause(1000); // Espera 1 segundo (1000 milisegundos)
        const buttonElement = await $('//android.widget.TextView[@content-desc="artistaDetalle"]');
        if (!buttonElement) {
            throw new Error('No se encontró el botón "Detalles"');
        }
        await buttonElement.click();
      });


      it('should display the detail Artist name on the screen', async function() {
          const nameElement = await $('//android.widget.TextView[@content-desc="artisNameDetail"]');
          if (!nameElement) {
              throw new Error('No se encontró el elemento nombre del artista en el detalle.');
          }
          const nameText = await nameElement.getAttribute('text');
          assert.isNotEmpty(nameText, 'Rubén Blades Bellido de Luna');
          assert.include(nameText, 'Rubén Blades Bellido de Luna');
      });


      it('should display the cover of artist detail on the screen', async function() {
          const coverElement = await $$('//android.widget.ImageView[@content-desc="artisCoverDetail"]')[0];
          assert.isOk(coverElement, 'No se encontró el elemento de la portada del artista');
          const contentDesc = await coverElement.getAttribute('content-desc');
          assert.isNotEmpty(contentDesc, 'El elemento de la portada del artista no tiene un valor para el atributo content-desc');
      });


      it('should display the detail description artist on the screen', async function() {
          const descripElement = await $('//android.widget.TextView[@content-desc="artisDescription"]');
          if (!descripElement) {
              throw new Error('No se encontró el elemento del descrip del detalle del artista');
          }
          const descripText = await descripElement.getAttribute('text');
          assert.isNotEmpty(descripText, 'Es un cantante, compositor,');
          assert.include(descripText, 'Es un cantante, compositor,');
      });

      it('should display the detail artist albums on the screen', async function() {
          const albumElement = await $('//android.widget.TextView[@content-desc="artisAlbumDetail"]');
          if (!albumElement) {
              throw new Error('No se encontró el elemento la cancion del detalle del álbum');
          }
          const albumText = await albumElement.getAttribute('text');
          assert.isNotEmpty(albumText, 'Buscando América');
          assert.include(albumText, 'Buscando América');
      });
});