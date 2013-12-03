
// Przyk�adowe u�ycie:
//
// Niech count to liczba element�w, picturesPaths to tablica z scie�kami do obrazk�w, repicesIds to tablica z identyfikatorami przepis�w, document to obiekt pliku html.
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

// funkcja definiowana przez onClickFunctionName (np. openRepice) musi przyjmowac tylko jeden argument kt�ry jest identyfikatorem przepisu (np. openRepice(repiceId))
LayoutContainer.prototype.CreateElementContent = function (pictureUrl, onClickFunctionName, imageCss, repiceId) {
    var content = '';

    content += '<img src="' + pictureUrl + '" class="' + imageCss + '" onclick="' + onClickFunctionName + '(' + repiceId.toString() + ')' + '"></img>';

    return content;
}

// document - dokument html-owy, na kt�rym znajduje si� kontener, content - dane zwr�cone przez funkcj� CreateElementContent
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

// przywraca funkcj� FillContainer do punktu pocz�tkowego
LayoutContainer.prototype.ResetFilling = function ()
{
    this.cellId = 0;
    this.rowId = 'first';
}

// wype�nienie konkretnego elementu z dokumnetu, je�li chcemy ustawi� okre�lon� kom�rk� w tabeli to id musi by� w formie <idWiersza><nrKolumnyLiczonyOd0>
LayoutContainer.prototype.FillContainerByGivenId = function( id, content)
{
    document.getElementById(id).outerText = content;
}

