
  import { assert } from 'chai'
  describe("test albums list",() => {
    beforeEach (async () =>{

    await $('//android.widget.TextView[@text="Albums"]').click();
    await browser.pause(2000); // Espera 1 segundo (1000 milisegundos)

    },{ shortCircuit: true });

      it('should display the album title on the screen', async function() {
          const titleElement = await $('//android.widget.TextView[@content-desc="albumTitle"]');
          if (!titleElement) {
              throw new Error('No se encontró el elemento del título del álbum');
          }
          const titleText = await titleElement.getAttribute('text');
          assert.isNotEmpty(titleText, 'Buscando América');
          assert.include(titleText, 'Buscando América');
          
      });

      it('should display the album performer on the screen', async function() {
          const performerElement = await $('//android.widget.TextView[@content-desc="albumPerformers"]');
          if (!performerElement) {
              throw new Error('No se encontró el elemento del performer del álbum');
          }
          const performerText = await performerElement.getAttribute('text');
          assert.isNotEmpty(performerText, 'Rubén Blades Bellido de Luna');
          assert.include(performerText, 'Rubén Blades Bellido de Luna');
      });

      it('should display the button detalles on the screen', async function() {
          const buttonElement = await $('//android.widget.TextView[@content-desc="btnTextAlbum"]');
          if (!buttonElement) {
              throw new Error('No se encontró el elemento del boton de detalles del album');
          }
          const buttonText = await buttonElement.getAttribute('text');
          assert.isNotEmpty(buttonText, 'Detalles');
          assert.include(buttonText, 'Detalles');
      });

      it('should display the thir album performer on the screen', async function() {
          const performerElement = await $('(//android.widget.TextView[@content-desc="albumPerformers"])[3]');
          if (!performerElement) {
              throw new Error('No se encontró el elemento del performer del álbum');
          }
          const performerText = await performerElement.getAttribute('text');
          assert.isNotEmpty(performerText, 'Queen');
          assert.include(performerText, 'Queen');
      });



});