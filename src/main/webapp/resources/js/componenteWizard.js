function procesarWzard(args) {
    if (args.validationFailed) {
        console.log('falta datos');
    } else {
        assignWizardClickEvents();
    }
}
function assignWizardClickEvents() {
    $('.ui-wizard-step-titles li').click(function () {
        var index = $(this).index();
        console.log(index);
        increment([{name: 'x', value: index}]);
        console.log(77);
        //$('#divWzard').load('extorsion089.xhtml');
        //actualizarDiv();
    });
}
$(document).ready(function () {
    assignWizardClickEvents();
});
//                    function actualizarDiv() {
//                        PrimeFaces.ajax.Request.handle({
//                            source: 'formDenuncia:divWzard',
//                            process: 'formDenuncia:divWzard',
//                            update: 'formDenuncia:divWzard',
//                            oncomplete: function (xhr, status, args) {
//                                console.log('Div actualizado');
//                            },
//                            params: [
//                                {name: 'javax.faces.partial.ajax', value: true},
//                                {name: 'javax.faces.source', value: 'formDenuncia:divWzard'},
//                                {name: 'javax.faces.partial.execute', value: 'formDenuncia:divWzard'},
//                                {name: 'javax.faces.partial.render', value: 'formDenuncia:divWzard'},
//                                {name: 'formDenuncia:divWzard', value: 'true'}
//                            ]
//                        });
//                    }



function soloLetras(e) {
    const char = String.fromCharCode(e.which);
    const regex = /^[A-Za-záéíóúüñÁÉÍÓÚÜÑ\s.,;:!@#\$%\^&\*\(\)\'\"\-\/]*$/; // Permitir letras, espacios y caracteres especiales

    // Permitir teclas especiales: Backspace, Tab, etc.
    if (e.which === 8 || e.which === 9) {
        return true;
    }
    return regex.test(char);
}