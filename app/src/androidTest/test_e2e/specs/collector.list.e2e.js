
  import { assert } from 'chai'
  describe("tests Collector list",() => {
    beforeEach (async () =>{

    await $('//android.widget.TextView[@text="Coleccionistas"]').click();
    await browser.pause(1000); // Espera 1 segundo (1000 milisegundos)

    },{ shortCircuit: true });

      it('should display the Collector Name on the list screen', async function() {
          const nameElement = await $('//android.widget.TextView[@content-desc="collectorName"]');
          if (!nameElement) {
              throw new Error('No se encontró el elemento del nombre del colector');
          }
          const nameText = await nameElement.getAttribute('text');
          assert.isNotEmpty(nameText, 'Manolo Bellon');
          assert.include(nameText, 'Manolo Bellon');
          
      });

      it('should display the second Collector Name on the list screen', async function() {
          const nameElement = await $$('//android.widget.TextView[@content-desc="collectorName"]');
          if (!nameElement) {
              throw new Error('No se encontró el elemento del nombre del colector');
          }
          const nameText = await nameElement[1].getAttribute('text');
          assert.isNotEmpty(nameText, 'Jaime Monsalve');
          assert.include(nameText, 'Jaime Monsalve');

      });

      it('should display the thir Collector Name on the list screen', async function() {
          const nameElement = await $$('//android.widget.TextView[@content-desc="collectorName"]');
          if (!nameElement) {
              throw new Error('No se encontró el elemento del nombre del colector');
          }
          const nameText = await nameElement[2].getAttribute('text');
          assert.isNotEmpty(nameText, 'Lucas Davis');
          assert.include(nameText, 'Lucas Davis');

      });
      it('should display the button detalles of collector on the screen', async function() {
          const buttonElement = await $('//android.widget.TextView[@content-desc="collectorButtonDetail"]');
          if (!buttonElement) {
              throw new Error('No se encontró el elemento del boton de detalles del collector');
          }
          const buttonText = await buttonElement.getAttribute('text');
          assert.isNotEmpty(buttonText, 'Ver Detalle');
          assert.include(buttonText, 'Ver Detalle');
      });


});