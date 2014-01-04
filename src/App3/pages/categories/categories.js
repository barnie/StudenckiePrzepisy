
//dostaje w options tablice z kategoriami jakie ma wyswietlic [i][0] -id [i][1] - nazwa 

(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/categories/categories.html", {
        // This function is called whenever a user navigates to this page. It
        // populates the page elements with the app's data.
        ready: function (element, options) {
            function LayoutContainer(size, tableCss, rowCss, cellCss) {
                this.width = Math.ceil(size / 2.0);
                this.tableCss = tableCss;
                this.rowCss = rowCss;
                this.cellCss = cellCss;
                this.cellId = 0;
                this.rowId = 'first';
            }

            // tworzenie tabeli przechowuj�cej przepisy
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

            // funkcja definiowana przez onClickFunctionName (np. openRepice) musi przyjmowac tylko jeden argument kt�ry jest identyfikatorem przepisu (np. openRepice(repiceId))
            LayoutContainer.prototype.CreateElementContent = function (pictureUrl, onClickFunctionName, imageCss, catName, catId) {
                var content = '';

                var imgPath = "/images/"; //TU NALEZY PODAC SCIEZKE DO OBRAZKOW ZAMIAST "images/"
                content += '<img src="' + imgPath + pictureUrl + '" class="' + imageCss + '" onclick="' + onClickFunctionName + '(' + catId.toString() + ')' + '"></img>';
                content += '<span class="catTitle">' + catName + '</span>';

                return content;
            }



            //script:

            //date prepare:
            var categories = options;
            var count = options.length;
            //picturesPaths bedzie pobierane z bazy (kazda kategoria bedzie miala swoj obrazek, niezalezny od przepisow), narazie przykladowe wart:
                var picturesPaths = [ "miecho.jpg", "ryba.jpg", "nabial.jpg", "warzywa.jpg", "owoce.jpg", "pieczywo.jpg", "grzyby.jpg", "soup.jpg", "sos.jpg", "przetwory.jpg", "deser.jpg", "napoj.jpg", "salatka.jpg", "studenckie.jpg"];
            //date prepared, start script:
            var layout = new LayoutContainer(count, 'myTabCss', 'myRowCss', 'myCellCss');
            var table = layout.CreateContainer();

            if (count < 3)
                document.getElementById('myDiv').innerHTML = window.toStaticHTML('<br><br>' + table.toString());
            else
                document.getElementById('myDiv').innerHTML = window.toStaticHTML(table.toString());
            var prefix = 'first';
            var j = 0;

            for (var i = 0; i < count; i++) {
                    var image = layout.CreateElementContent(picturesPaths[i], 'openWindowWithRepice', 'myImageCss', categories[i][1], categories[i][0]);
                if (j == layout.width) {
                    j = 0;
                    prefix = "second";
                }

                document.getElementById(prefix + j.toString()).innerHTML = window.toStaticHTML(image.toString());
                    document.getElementById(prefix + j.toString()).addEventListener("click", console.log("siema" + i) );
                j++;
            }
        },

        unload: function () {
            // TODO: Respond to navigations away from this page.
        },

        updateLayout: function (element, viewState, lastViewState) {
            /// <param name="element" domElement="true" />

            // TODO: Respond to changes in viewState.
        }
    });
})();
