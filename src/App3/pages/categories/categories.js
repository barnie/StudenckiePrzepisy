
//dostaje w options tablice z kategoriami jakie ma wyswietlic [i][0] -id [i][1] - nazwa , [i][2] - zdjecie

(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/categories/categories.html", {
        ready: function (element, options) {
            
            //script:

            //date prepare:
            var categories = options;
            var count = options.length;
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
            var nameTagsIds = new Array();
            for (var i = 0; i < count; i++) {
                var image = layout.CreateElementContent(prefix + j.toString(), categories[i][2], 'myImageCss', categories[i][0], categories[i][1]);
                imgTagsIds[i] = prefix + j.toString() + 'img';
                nameTagsIds[i] = prefix + j.toString() + 'name';
                if (j == layout.width) {
                    j = 0;
                    prefix = "second";
                }

                document.getElementById(prefix + j.toString()).innerHTML = window.toStaticHTML(image.toString());
                j++;
            }
            for (var i = 0; i < count; i++) {
                element.querySelector('#' + imgTagsIds[i]).onclick = this.loadRecipe;
                element.querySelector('#' + nameTagsIds[i]).onclick = this.loadRecipe;
            }
            //additional data for css prepare:
            document.getElementById("container").style.borderSpacing = "" + window.screen.availWidth * 0.028 + "px 0 ";


        },
        loadRecipe: function (arg) {
            arg = $(this).data('arg');
            // TODO 2: Tutaj umiesc kod ktory otworzy nowa strone albo wykona cos na podstawie tego arg ktory podales w funkcji CreateElementContent > repicesIds[i]
            var array = [];
            getPrzepisyKat(arg, array).then(function () {
                loadRecipiesList(array); //ladujemy liste przepisow
                WinJS.Navigation.navigate( "pages/list_recipes/list_recipes.html" , array );
            })
            // TODO 2
        },
        unload: function () {
            // TODO: Respond to navigations away from this page.
        }
    });
})();

