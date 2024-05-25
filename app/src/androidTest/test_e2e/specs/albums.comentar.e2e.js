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


    it('should display the detail album write comment on the screen', async function() {
        const textcommentElement = await $('//android.view.View[@content-desc="textFieldcomentAlbumsDetail"]');
        await textcommentElement.click();
        await browser.pause(1000); // Espera 1 segundo (1000 milisegundos)
        await browser.keys('Buen Album'); // Simular la entrada de texto
        await browser.pause(2000); // Espera 2 segundos (2000 milisegundos)
        await textcommentElement.click();
        const commetTextValue = await $('//android.widget.EditText[@text="Buen Album"]').getAttribute('text');
        assert.isNotEmpty(commetTextValue, 'Buen Album');
        assert.include(commetTextValue, 'Buen Album');
    });

    it('should display the detail album comment album on the screen', async function() {
        const textcommentElement = await $('//android.view.View[@content-desc="textFieldcomentAlbumsDetail"]');
        await textcommentElement.click();
        await browser.pause(1000); // Espera 1 segundo (1000 milisegundos)
        await browser.keys(', me encanta'); // Simular la entrada de texto
        await browser.pause(1000); // Espera 2 segundos (2000 milisegundos)
        await driver.back();
        await browser.pause(2000); // Espera 2 segundos (2000 milisegundos)
        const commetTextValue = await $('//android.widget.EditText[@text="Buen Album, me encanta"]').getAttribute('text');
        await browser.pause(1000); // Espera 2 segundos (2000 milisegundos)
        assert.isNotEmpty(commetTextValue, 'Buen Album, me encanta');
        assert.include(commetTextValue, 'Buen Album, me encanta');
        await $('//android.widget.TextView[@content-desc="textBtnComentAlbumsDetail"]').click();
        const commentsAlbum = await $$('//android.widget.TextView[@content-desc="commentAlbumsDetail"]');
        const commentAlbum = await commentsAlbum[1].getAttribute('text');
        assert.isNotEmpty(commentAlbum, 'me encanta');
        assert.include(commentAlbum, 'me encanta');

    });

});
