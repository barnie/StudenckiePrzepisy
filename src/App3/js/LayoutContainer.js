
// Przyk³adowe u¿ycie:
//
// Niech count to liczba elementów, picturesPaths to tablica z scie¿kami do obrazków, repicesIds to tablica z identyfikatorami przepisów, document to obiekt pliku html.
//
//      var layout = new LayoutContainer(count, 'myTableCss', 'myRowCss', 'myCellCss');
//
//      var table = layout.CreateContainer();
//
//      layout.FillContainerByGivenId(document, 'myDiv', table);
//
//      for (var i = 0; i < count; i++)
//      {
//          var image = layout.CreateElementContent(picturesPaths[i],'openWindowWithRepice','myImageCss',repicesIds[i]);
//          layout.FillContainer(document, image)
//      }
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
        container += '<td id="first' + i.toString() + '" class="' + this.cellCss + '">hhh</td>';
    }

    container += '</tr><tr id="second" class="' + this.rowCss + '">';

    for (var i = 0; i < this.width; i++)
    {
        container += '<td id="second' + i.toString() + '" class="' + this.cellCss + '">ffff</td>';
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

// document - dokument html-owy, na którym znajduje siê kontener, content - dane zwrócone przez funkcjê CreateElementContent
LayoutContainer.prototype.FillContainer = function(dd, content)
{
    var currentCellId = '';

    if (this.cellId == this.width)
    {
        this.cellId = 0;

        this.rowId = 'second';
    }

    currentCellId = this.rowId + this.cellId.toString();

    dd.getElementById(currentCellId).outerText = content;

    this.cellId += 1;
}

// przywraca funkcjê FillContainer do punktu pocz¹tkowego
LayoutContainer.prototype.ResetFilling = function ()
{
    this.cellId = 0;
    this.rowId = 'first';
}

// wype³nienie konkretnego elementu z dokumnetu, jeœli chcemy ustawiæ okreœlon¹ komórkê w tabeli to id musi byæ w formie <idWiersza><nrKolumnyLiczonyOd0>
LayoutContainer.prototype.FillContainerByGivenId = function( id, content)
{
    document.getElementById(id).outerText = content;
}

