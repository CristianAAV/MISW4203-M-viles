
  import { assert } from 'chai'
  describe("title app",() => {
    beforeEach (async () =>{

    await browser.pause(2000); // Espera 1 segundo (1000 milisegundos)
    await $('//android.widget.TextView[@text="Artistas"]').click();
    await browser.pause(2000); // Espera 1 segundo (1000 milisegundos)
    await $('//android.widget.TextView[@text="Albums"]').click();
    await browser.pause(2000); // Espera 1 segundo (1000 milisegundos)

    },{ shortCircuit: true });


    it('should display the app title on the screen', async function() {
      const titleElement = await $('//android.widget.TextView[@text="Vinilos"]');
      if (!titleElement) {
          throw new Error('No se encontró el elemento del título de la app');
      }
      const titleText = await titleElement.getText();
      assert.isNotEmpty(titleText, 'Vinilos');
    });


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

      it('should navigate to details activity when "Detalles" button is clicked', async function() {
        const buttonElement = await $('//android.widget.TextView[@text="Detalles"]');
        if (!buttonElement) {
            throw new Error('No se encontró el botón "Detalles"');
        }
        await buttonElement.click();

        // Espera a que un elemento específico aparezca en la nueva actividad
        await $('//android.widget.TextView[@text="Artista:"]').waitForExist({ timeout: 10000 });

        // Verificar que estamos en la nueva actividad
        const newElement = await $('//android.widget.TextView[@text="Artista:"]');
        assert.isTrue(await newElement.isExisting(), 'No se encontró el nuevo elemento, la nueva actividad no se cargó correctamente');
     });

});