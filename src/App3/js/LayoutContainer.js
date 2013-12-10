
// Przyk�adowe u�ycie:
// Wklejasz to do pliku html gdzie ma to dzia�a� !!!
// Niech count to liczba element�w, picturesPaths to tablica z scie�kami do obrazk�w, repicesIds to tablica z identyfikatorami przepis�w, document to obiekt pliku html.
//
// window.onload = function ()
// {
//     var count = 3;
//     var layout = new LayoutContainer(count, 'myTableCss', 'myRowCss', 'myCellCss');
//     
//     var table = layout.CreateContainer();
//
//     document.getElementById('myDiv').innerHTML = window.toStaticHTML(table.toString());
//     var prefix = 'first';
//     var j = 0;
//     var picturesPaths = ["images/1.jpg", "images/2.jpg", "images/3.jpg"];
//     var repicesIds = ["1", "2", "3"];
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
// W wyniku dzia�ania takiego kodu otrzymujemy na html-u okre�lonym przez document nasz� tablic� z obrazkami, wraz z zbindowan� funkcj� do otwarcia nowego okna z okre�lonym id przepisu

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

// tworzenie tabeli przechowuj�cej przepisy
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

// funkcja definiowana przez onClickFunctionName (np. openRepice) musi przyjmowac tylko jeden argument kt�ry jest identyfikatorem przepisu (np. openRepice(repiceId))
LayoutContainer.prototype.CreateElementContent = function (pictureUrl, onClickFunctionName, imageCss, repiceId) {
    var content = '';

    content += '<img src="' + pictureUrl + '" class="' + imageCss + '" onclick="' + onClickFunctionName + '(' + repiceId.toString() + ')' + '"></img>';

    return content;
}



