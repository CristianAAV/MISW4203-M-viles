
  import { assert } from 'chai'
  describe("tests Artist list",() => {
    beforeEach (async () =>{

    await $('//android.widget.TextView[@text="Artistas"]').click();
    await browser.pause(2000); // Espera 1 segundo (1000 milisegundos)

    },{ shortCircuit: true });

      it('should display the Artista Name on the list screen', async function() {
          const nameElement = await $('//android.widget.TextView[@content-desc="artistaName"]');
          if (!nameElement) {
              throw new Error('No se encontró el elemento del nombre del artista');
          }
          const nameText = await nameElement.getAttribute('text');
          assert.isNotEmpty(nameText, 'Rubén Blades Bellido de Luna');
          assert.include(nameText, 'Rubén Blades Bellido de Luna');
          
      });

      it('should display the artist name on the screen', async function() {
          const artistElement = await $('//android.widget.TextView[@content-desc="artistaName"]');
          if (!artistElement) {
              throw new Error('No se encontró el elemento nombre del artista');
          }
          const artistext = await artistElement.getAttribute('text');
          assert.isNotEmpty(artistext, 'Rubén Blades Bellido de Luna');
          assert.include(artistext, 'Rubén Blades Bellido de Luna');
      });

      it('should display the second artist name  on the screen', async function() {
          const artistElement = await $$('//android.widget.TextView[@content-desc="artistaName"]');
          if (!artistElement) {
              throw new Error('No se encontró el elemento nombre del artista');
          }
          const artistext = await artistElement[1].getAttribute('text');
          assert.isNotEmpty(artistext, 'The Blue Notes');
          assert.include(artistext, 'The Blue Notes');
      });

      it('should display the button detalles of artista on the screen', async function() {
          const buttonElement = await $('//android.widget.TextView[@content-desc="artistaDetalle"]');
          if (!buttonElement) {
              throw new Error('No se encontró el elemento del boton de detalles del artista');
          }
          const buttonText = await buttonElement.getAttribute('text');
          assert.isNotEmpty(buttonText, 'Ver Detalles');
          assert.include(buttonText, 'Ver Detalles');
      });

      it('should display the cover of artist on the screen', async function() {
          const coverElement = await $$('//android.widget.ImageView[@content-desc="artistaCover"]')[0];
          assert.isOk(coverElement, 'No se encontró el elemento de la portada del artista');
          const contentDesc = await coverElement.getAttribute('content-desc');
          assert.isNotEmpty(contentDesc, 'El elemento de la portada del artista no tiene un valor para el atributo content-desc');
      });



});