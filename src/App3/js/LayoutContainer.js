
// Przyk³adowe u¿ycie:
// Wklejasz to do pliku html gdzie ma to dzia³aæ !!!
// Niech count to liczba elementów, picturesPaths to tablica z scie¿kami do obrazków, repicesIds to tablica z identyfikatorami przepisów, document to obiekt pliku html.
//
// window.onload = function ()
// {
//     var count = 3;
//     var layout = new LayoutContainer(count, 'myTableCss', 'myRowCss', 'myCellCss');
     
//     var table = layout.CreateContainer();

//     document.getElementById('myDiv').innerHTML = window.toStaticHTML(table.toString());
//     var prefix = 'first';
//     var j = 0;
//     var picturesPaths = ["images/1.jpg", "images/2.jpg", "images/3.jpg"];
//     var repicesIds = ["1", "2", "3"];

//     var functions = layout.CreateAndRegisterFunctionHandlers(repicesIds, count); // <---------- Tutaj <----------------

//     for (var i = 0; i < count; i++)
//     {
//         var image = layout.CreateElementContent(picturesPaths[i], functions[i], 'myImageCss', repicesIds[i]); // <------------------- Tutaj <-------------------
//         if (j == layout.width)
//         {
//             j = 0;
//             prefix = 'second';
//         }
//         document.getElementById(prefix + j.toString()).innerHTML = window.toStaticHTML(image.toString());
//         j++;
//     }
//}

//W wyniku dzia³ania takiego kodu otrzymujemy na html-u okreœlonym przez document nasz¹ tablicê z obrazkami, wraz z zbindowan¹ funkcj¹ do otwarcia nowego okna z okreœlonym id przepisu

// konstruktor
function LayoutContainer(size, tableCss, rowCss, cellCss)
{
    this.width = Math.ceil(size / 2.0);
    this.tableCss = tableCss;
    this.rowCss = rowCss;
    this.cellCss = cellCss;
    this.cellId = 0;
    this.rowId = 'first';
}

// tworzenie tabeli przechowuj¹cej przepisy
LayoutContainer.prototype.CreateContainer = function()
{
    var container = '';

    container += '<table id="container" class="' + this.tableCss + '"><tr id="first" class="' + this.rowCss + '">';

    for(var i=0;i<this.width;i++)
    {
        container += '<td id="first' + i.toString() + '" class="' + this.cellCss + '"></td>';
    }

    container += '</tr><tr id="second" class="' + this.rowCss + '">';

    for (var i = 0; i < this.width; i++)
    {
        container += '<td id="second' + i.toString() + '" class="' + this.cellCss + '"></td>';
    }

    container += '</tr></table>';

    return container;
}

LayoutContainer.prototype.CreateElementContent = function (pictureUrl, onClickFunctionName, imageCss) {
    var content = '';

    content += '<img src="' + pictureUrl + '" class="' + imageCss + '" onclick="' + onClickFunctionName+ '"></img>';

    return content;
    
}

//Musisz wyedtyowa? t? funkcj? zmieniaj?c komentarz na kod który si? wykona np:
//
//   script.text = 'function ' + functionName + '{ WinJS.navigation.navigate(idArray[i]); }';
//
LayoutContainer.prototype.CreateAndRegisterFunctionHandlers = function (idArray, lenght)
{
    var functionNames = new Array();
    for(var i=0; i<lenght; i++)
    {
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



