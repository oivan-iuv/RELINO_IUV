function handleSubmit(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    
    if (args.validationFailed) {
        
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).hide();
    }
}
function handleSubmitAbrir(args, dialog) {
    var jqDialog = jQuery('#' + dialog);
    if (args.validationFailed) {
        alert('ok');
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        PF(dialog).show();
    }
}
function validDlg(args, dialog) {
    if (args.validacion) {
        alert('ok');
        console.log("estabien este");
        PF(dialog).hide();
    }
}
function validAbrirDlg(args, dialog) {
    if (args.validacionAbrirDlg) {

        console.log("estabien este");
        PF(dialog).show();
    }
}




function updatecontarCaracteres(textarea, contadorId) {
    var maxLength = textarea.getAttribute('maxlength');
    var numeroCaracteresActual = textarea.value.length;
    var contardor = document.getElementById(contadorId);
    contardor.textContent = numeroCaracteresActual + ' / ' + maxLength + ' caracteres';
}


