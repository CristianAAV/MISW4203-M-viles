import { assert } from 'chai';

describe("Test Comment Album", () => {
    beforeEach(async () => {
        await browser.pause(1000); // Espera 1 segundo (1000 milisegundos)
    });

    it('should navigate to details activity when "Enviar" exits', async function() {
        await $('//android.widget.TextView[@text="Albumes"]').click();
        await browser.pause(1000); // Espera 1 segundo (1000 milisegundos)
        await $('(//android.widget.TextView[@content-desc="btnTextAlbum"])').click();
        await browser.pause(1000); // Espera 1 segundo (1000 milisegundos)
        const buttonSendElement = await $('//android.widget.TextView[@content-desc="textBtnComentAlbumsDetail"]');
        const buttonText = await buttonSendElement.getAttribute('text');
        assert.isNotEmpty(buttonText, 'Enviar');
        assert.include(buttonText, 'Enviar');
    });


    it('should display the detail album text label commets on the screen', async function() {
        const textLblCommentElement = await $('//android.widget.TextView[@content-desc="textLabelComment"]');
        if (!textLblCommentElement) {
          throw new Error('No se encontró el elemento del comentario del detalle del álbum');
        }
        const commetLblText = await textLblCommentElement.getAttribute('text');
        assert.isNotEmpty(commetLblText, 'Comentarios:');
        assert.include(commetLblText, 'Comentarios:');
    });

    it('should display the detail album text comments on the screen', async function() {
        const textcommentElement = await $('//android.widget.TextView[@content-desc="labelComentarios"]');
        const commetText = await textcommentElement.getText();
        assert.isNotEmpty(commetText, 'Comentarios');
        assert.include(commetText, 'Comentarios');
    });



});
