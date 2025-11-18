
function normalizeText(input) {
    // Paso 1: Convertir a mayúsculas
    let output = input.toUpperCase();

    // Paso 2: Reemplazar acentos y caracteres especiales
    output = output.replace(/[ÁÀÂÄ]/g, 'A')
            .replace(/[ÉÈÊË]/g, 'E')
            .replace(/[ÍÌÎÏ]/g, 'I')
            .replace(/[ÓÒÔÖ]/g, 'O')
            .replace(/[ÚÙÛÜ]/g, 'U')
            .replace(/[Ç]/g, 'C')
            .replace(/['"]/g, '')
            .replace(/['´']/g, '');

    // Paso 3: Filtrar solo caracteres permitidos (A-Z, 0-9)
    //output = output.replace(/[^A-Z0-9]/g, '');

    return output;
}


