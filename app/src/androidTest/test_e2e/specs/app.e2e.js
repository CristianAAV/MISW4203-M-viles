
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

    it('should display the app tab Albums on the screen', async function() {
      const tabElement = await $('//android.widget.TextView[@text="Albums"]');
      if (!tabElement) {
          throw new Error('No se encontró el elemento del tab de Albums de la app');
      }
      const tabText = await tabElement.getText();
      assert.isNotEmpty(tabText, 'Albums');
    });

    it('should display the app tab Artistas on the screen', async function() {
      const tabElement = await $('//android.widget.TextView[@text="Artistas"]');
      if (!tabElement) {
          throw new Error('No se encontró el elemento del tab de Artistas de la app');
      }
      const tabText = await tabElement.getText();
      assert.isNotEmpty(tabText, 'Artistas');
    });

    it('should display the app tab Coleccionistas on the screen', async function() {
      const tabElement = await $('//android.widget.TextView[@text="Coleccionistas"]');
      if (!tabElement) {
          throw new Error('No se encontró el elemento del tab de Coleccionistas de la app');
      }
      const tabText = await tabElement.getText();
      assert.isNotEmpty(tabText, 'Coleccionistas');
    });
});