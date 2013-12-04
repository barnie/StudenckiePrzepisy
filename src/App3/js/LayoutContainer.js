
// Przyk³adowe u¿ycie:
// Wklejasz to do pliku html gdzie ma to dzia³aæ !!!
// Niech count to liczba elementów, picturesPaths to tablica z scie¿kami do obrazków, repicesIds to tablica z identyfikatorami przepisów, document to obiekt pliku html.
//
// window.onload = function ()
// {
//     var layout = new LayoutContainer(count, 'myTableCss', 'myRowCss', 'myCellCss');
//
//     var table = layout.CreateContainer();
//
//     document.getElementById('myDiv').innerHTML = window.toStaticHTML(table.toString());
//     var prefix = 'first';
//     var j = 0;
//     for (var i = 0; i < count; i++)
//     {
//         var image = layout.CreateElementContent(picturesPaths[i], 'openWindowWithRepice', 'myImageCss', repicesIds[i]);
//         if (j == layout.width)
//         {
//             j = 0;
//             prefix = 'second';
//         }
//         document.getElementById(prefix + j.toString()).innerHTML = window.toStaticHTML(image.toString());
//         j++;
//     }
//}
//
// W wyniku dzia³ania takiego kodu otrzymujemy na html-u okreœlonym przez document nasz¹ tablicê z obrazkami, wraz z zbindowan¹ funkcj¹ do otwarcia nowego okna z okreœlonym id przepisu

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

// funkcja definiowana przez onClickFunctionName (np. openRepice) musi przyjmowac tylko jeden argument który jest identyfikatorem przepisu (np. openRepice(repiceId))
LayoutContainer.prototype.CreateElementContent = function (pictureUrl, onClickFunctionName, imageCss, repiceId) {
    var content = '';

    content += '<img src="' + pictureUrl + '" class="' + imageCss + '" onclick="' + onClickFunctionName + '(' + repiceId.toString() + ')' + '"></img>';

    return content;
}



