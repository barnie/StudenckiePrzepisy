var DecInf = { //interface, decorator pattern (for future):
    CreateContainer: function () { },
    CreateElementContent: function (cellId, pictureUrl, imageCss, pictureId, catName) { },
    CreateAndRegisterFunctionHandlers: function (idArray, lenght) { }
};



function LayoutContainer() { //constructor
    
    if (arguments.callee._singletonInstance) //singleton
        return arguments.callee._singletonInstance;
    arguments.callee._singletonInstance = this;

    this.SetValue = function (size, tableCss, rowCss, cellCss) { //Dependency Injection
        this.width = Math.ceil(size / 2.0);
        this.tableCss = tableCss;
        this.rowCss = rowCss;
        this.cellCss = cellCss;
        this.cellId = 0;
        this.rowId = 'first';
    }
}

LayoutContainer.prototype = Object.create(DecInf); //extend DecInfo for LayoutContainer

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

    content += '<img id="' + cellId + 'img" src="' + "/images/" + pictureUrl + '" class="' + imageCss + '" data-arg="' + pictureId + '"></img>';
    content += '<span class="catName" id="' + cellId + 'name"  data-arg="' + pictureId + '">' + catName + '</span>';
    return content;

}


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