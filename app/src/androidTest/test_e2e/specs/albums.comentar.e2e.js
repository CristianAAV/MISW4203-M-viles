
  import { assert } from 'chai'
  describe("Test Comment Album",() => {
    beforeEach (async () =>{


    },{ shortCircuit: true });

        it('should navigate to details activity when "Detalles" button is clicked', async function() {
        await browser.pause(1000); // Espera 1 segundo (1000 milisegundos)
        await $('//android.widget.TextView[@text="Albums"]').click();
        await browser.pause(1000); // Espera 1 segundo (1000 milisegundos)
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