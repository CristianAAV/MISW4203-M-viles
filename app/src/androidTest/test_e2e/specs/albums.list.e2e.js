
  import { assert } from 'chai'
  describe("title albums list",() => {
    beforeEach (async () =>{

    await browser.pause(2000); // Espera 1 segundo (1000 milisegundos)
    await $('//android.widget.TextView[@text="Artistas"]').click();
    await browser.pause(2000); // Espera 1 segundo (1000 milisegundos)
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
      });

      it('should display the album performer on the screen', async function() {
          const titleElement = await $('//android.widget.TextView[@content-desc="albumPerformers"]');
          if (!titleElement) {
              throw new Error('No se encontró el elemento del título del álbum');
          }
          const titleText = await titleElement.getAttribute('text');
          assert.isNotEmpty(titleText, 'Rubén Blades Bellido de Luna');
      });

});