
//dostaje w options tablice z kategoriami jakie ma wyswietlic [i][0] -id [i][1] - nazwa 

(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/categories/categories.html", {
        ready: function (element, options) {
            
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
                var imgTagsIds = new Array();
                for (var i = 0; i < count; i++) {
                    var image = layout.CreateElementContent(prefix + j.toString(), picturesPaths[i], 'myImageCss', categories[i][0]);
                    imgTagsIds[i] = prefix + j.toString() + 'img';
                    if (j == layout.width) {
                        j = 0;
                        prefix = "second";
                    }

                    document.getElementById(prefix + j.toString()).innerHTML = window.toStaticHTML(image.toString());
                    j++;
                }
                for (var i = 0; i < count; i++) {
                    element.querySelector('#' + imgTagsIds[i]).onclick = this.loadRecipe;
                }
        },
        loadRecipe: function (arg) {
            arg = $(this).data('arg');
            // TODO 2: Tutaj umiesc kod ktory otworzy nowa strone albo wykona cos na podstawie tego arg ktory podales w funkcji CreateElementContent > repicesIds[i]
            var array = [];
            getPrzepisyKat(arg, array).then(function () {
                loadRecipiesList(array); //ladujemy liste przepisow
                WinJS.Navigation.navigate( "pages/list_recipes/list_recipes.html" );
            })
            // TODO 2
        },
        unload: function () {
            // TODO: Respond to navigations away from this page.
        }
    });
})();

