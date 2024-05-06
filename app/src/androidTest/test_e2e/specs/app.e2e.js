
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
});