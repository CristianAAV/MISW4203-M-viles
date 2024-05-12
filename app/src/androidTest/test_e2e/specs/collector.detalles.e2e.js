
  import { assert } from 'chai'
  describe("test detail Collector",() => {
    beforeEach (async () =>{


    },{ shortCircuit: true });


      it('should navigate to details activity when "Detalles" button is clicked', async function() {

        await $('//android.widget.TextView[@text="Coleccionistas"]').click();
        await browser.pause(1000); // Espera 1 segundo (1000 milisegundos)
        const buttonElement = await $('//android.widget.TextView[@content-desc="collectorButtonDetail"]');
        if (!buttonElement) {
            throw new Error('No se encontró el botón "Detalles"');
        }
        await buttonElement.click();
      });


      it('should display the detail Collector name on the screen', async function() {
          const nameElement = await $('//android.widget.TextView[@content-desc="collectorNameDetail"]');
          if (!nameElement) {
              throw new Error('No se encontró el elemento nombre del collector en el detalle.');
          }
          const nameText = await nameElement.getAttribute('text');
          assert.isNotEmpty(nameText, 'Manolo Bellon');
          assert.include(nameText, 'Manolo Bellon');
      });


      it('should display the number of collector detail on the screen', async function() {
          const numberElement = await $$('//android.widget.TextView[@content-desc="collectorNumberDetail"]');
          if (!numberElement) {
              throw new Error('No se encontró el elemento numero del collector en el detalle.');
          }
          const numberText = await numberElement[1].getAttribute('text');
          assert.isNotEmpty(numberText, '3502457896');
          assert.include(numberText, '3502457896');
      });


      it('should display the email of collector detail on the screen', async function() {
          const emailElement = await $$('//android.widget.TextView[@content-desc="collectorEmailDetail"]');
          if (!emailElement) {
              throw new Error('No se encontró el elemento email del collector en el detalle.');
          }
          const emailText = await emailElement[1].getAttribute('text');
          assert.isNotEmpty(emailText, 'manollo@caracol.com.co');
          assert.include(emailText, 'manollo@caracol.com.co');
      });

      it('should display the album of collector detail on the screen', async function() {
          const albumsElement = await $('//android.widget.TextView[@content-desc="collectorAlbumDetail"]');
          if (!albumsElement) {
              throw new Error('No se encontró el elemento album del collector en el detalle.');
          }
          const albumsText = await albumsElement.getAttribute('text');
          assert.isNotEmpty(albumsText, 'Buscando América');
          assert.include(albumsText, 'Buscando América');
      });

      it('should display the artis of collector detail on the screen', async function() {
          const albumsElement = await $$('//android.widget.TextView[@content-desc="collectorArtistDetail"]');
          if (!albumsElement) {
              throw new Error('No se encontró el elemento artist del collector en el detalle.');
          }
          const albumsText = await albumsElement[0].getAttribute('text');
          assert.isNotEmpty(albumsText, 'Rubén Blades Bellido de Luna');
          assert.include(albumsText, 'Rubén Blades Bellido de Luna');
      });

});