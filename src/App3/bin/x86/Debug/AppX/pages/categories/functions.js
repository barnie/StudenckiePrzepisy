
function LayoutContainer(size, tableCss, rowCss, cellCss) {
    this.width = Math.ceil(size / 2.0);
    this.tableCss = tableCss;
    this.rowCss = rowCss;
    this.cellCss = cellCss;
    this.cellId = 0;
    this.rowId = 'first';
}

// tworzenie tabeli przechowującej przepisy
LayoutContainer.prototype.CreateContainer = function () {
    var container = '';

    container += '<table id="container" class="' + this.tableCss + '"><tr id="first" class="' + this.rowCss + '">';

    for (var i = 0; i < this.width; i++) {
        container += '<td id="first' + i.toString() + '" class="' + this.cellCss + '"></td>';
    }

    container += '</tr><tr id="second" class="' + this.rowCss + '">';

    for (var i = 0; i < this.width; i++) {
        container += '<td id="second' + i.toString() + '" class="' + this.cellCss + '"></td>';
    }

    container += '</tr></table>';

    return container;
}


LayoutContainer.prototype.CreateElementContent = function (cellId, pictureUrl, imageCss, pictureId, catName) {
    var content = '';

    content += '<img id="' + cellId + 'img" src="' + MyGlobals.imagesPath + pictureUrl + '" class="' + imageCss + '" data-arg="' + pictureId + '"></img>';
    content += '<span class="catName" id="' + cellId + 'name"  data-arg="' + pictureId + '">' + catName + '</span>';
    return content;

}


/*
LayoutContainer.prototype.CreateElementContent = function (pictureUrl, imageCss, catName, catId) {
    var content = '';

    var imgPath = "/images/"; //TU NALEZY PODAC SCIEZKE DO OBRAZKOW ZAMIAST "images/"
    content += '<img id="' + catId + '" src="' + imgPath + pictureUrl + '" class="' + imageCss + '" onclick="' + onClickFunctionName + '(' + catId.toString() + ')' + '"></img>';
    content += '<span class="catTitle">' + catName + '</span>';

    return content;
}
*/

LayoutContainer.prototype.CreateAndRegisterFunctionHandlers = function (idArray, lenght) {
    var functionNames = new Array();
    for (var i = 0; i < lenght; i++) {
        var head = document.getElementsByTagName('head')[0];
        var script = document.createElement('script');
        var functionName = 'OpenRecipe' + idArray[i] + '()';
        script.type = 'text/javascript';
        script.text = 'function ' + functionName + '{/*kod który otworzy to co chcesz*/}';
        head.appendChild(script);
        functionNames[i] = functionName;
    }
    return functionNames;
}