
  import { assert } from 'chai'
  describe("Test Albums Detail",() => {
    beforeEach (async () =>{


    },{ shortCircuit: true });


      it('should navigate to details activity when "Detalles" button is clicked', async function() {
        await browser.pause(2000); // Espera 1 segundo (1000 milisegundos)
        await $('//android.widget.TextView[@text="Albumes"]').click();
        await browser.pause(2000); // Espera 1 segundo (1000 milisegundos)
        const buttonElement = await $('//android.widget.TextView[@text="Ver detalle"]');
        if (!buttonElement) {
            throw new Error('No se encontró el botón "Ver detalle"');
        }
    await buttonElement.click();
        // Espera a que un elemento específico aparezca en la nueva actividad
        await $('//android.widget.TextView[@text="Artista: "]').waitForExist({ timeout: 10000 });
        // Verificar que estamos en la nueva actividad
        const newElement = await $('//android.widget.TextView[@text="Artista: "]');
        assert.isTrue(await newElement.isExisting(), 'No se encontró el nuevo elemento, la nueva actividad no se cargó correctamente');
      });


      it('should display the detail album title on the screen', async function() {
          const titleElement = await $('//android.widget.TextView[@content-desc="titleAlbumsDetail"]');
          if (!titleElement) {
              throw new Error('No se encontró el elemento del título del detalle del álbum');
          }
          const titleText = await titleElement.getAttribute('text');
          assert.isNotEmpty(titleText, 'Buscando América');
          assert.include(titleText, 'Buscando América');
      });


      it('should display the detail album performer on the screen', async function() {
          const performerElement = await $('//android.widget.TextView[@content-desc="albumPerformersDetail"]');
          if (!performerElement) {
              throw new Error('No se encontró el elemento del performer del detalle del álbum');
          }
          const performerText = await performerElement.getAttribute('text');
          assert.isNotEmpty(performerText, 'Rubén Blades Bellido de Luna');
          assert.include(performerText, 'Rubén Blades Bellido de Luna');
      });

      it('should display the detail album genre on the screen', async function() {
          const genreElement = await $('//android.widget.TextView[@content-desc="albumGenreDetail"]');
          if (!genreElement) {
              throw new Error('No se encontró el elemento del genero del detalle del álbum');
          }
          const genreText = await genreElement.getAttribute('text');
          assert.isNotEmpty(genreText, 'Salsa');
          assert.include(genreText, 'Salsa');
      });

      it('should display the detail album date on the screen', async function() {
          const dateElement = await $('//android.widget.TextView[@content-desc="albumDateDetail"]');
          if (!dateElement) {
              throw new Error('No se encontró el elemento la cancion del detalle del álbum');
          }
          const dateText = await dateElement.getAttribute('text');
          assert.isNotEmpty(dateText, '01');
          assert.include(dateText, '01');
      });
});