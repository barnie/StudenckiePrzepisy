
//dostaje w options tablice z kategoriami jakie ma wyswietlic [i][0] -id [i][1] - nazwa , [i][2] - zdjecie

(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/categories/categories.html", {
        ready: function (element, options) {
            //links handler:
            WinJS.Utilities.query("a").listen("click", anchorHandler, false);
            //date prepare:
            var categories = options;
            var count = options.length;
            //date prepared, start script:
            var layout = new LayoutContainer();
            layout.SetValue(count, 'myTabCss', 'myRowCss', 'myCellCss');
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
            document.getElementById("container").style.borderSpacing = "" + window.screen.availWidth * 0.028 + "px 0 "; //odstêpy miêdzy kategoriami
            document.getElementById("myDiv").style.width = window.screen.availWidth * 1.25 + "px"; //sprawiam ze tabelka bedzie wychodzic poza ekran (dolny scroll)
            console.log("szer=" + window.screen.availWidth * 1.25);


        },
        loadRecipe: function (arg) {
            arg = $(this).data('arg');
            // TODO 2: Tutaj umiesc kod ktory otworzy nowa strone albo wykona cos na podstawie tego arg ktory podales w funkcji CreateElementContent > repicesIds[i]
            var array = [];
            getPrzepisyKat(arg, array).then(function () {
                loadRecipiesList(array).then( function(){ //ladujemy liste przepisow
                    WinJS.Navigation.navigate("pages/list_recipes/list_recipes.html", array);
                })
            })
            // TODO 2
        },
        unload: function () {
            // TODO: Respond to navigations away from this page.
        }
    });

    function anchorHandler(eventInfo) { //jak sie w linka kliknie
        eventInfo.preventDefault();
        var link = eventInfo.target;
        if (("" + link).search("categories") != -1) { //jak link bedzie zawieral categories (bedzie wiec do categories)

            var array = [];

            getKategorie(array).then(function () {
                WinJS.Navigation.navigate(link.href, array);
            })

        }
        else if (("" + link).search("list_recipes") != -1) {

            var array = [];

            getPrzepisy(array).then(function () {
                loadRecipiesList(array).then( function() {
                    WinJS.Navigation.navigate(link.href, array); 
                })
            })

        }
        else if(("" + link).search("add_recipe") != -1) {

            var array = [];

            getKategorie(array).then(function () {
                for (var i = 0 ; i < array.length ; i++) {
                    categoryArray[i] = { title: array[i][1], picture: "/images/" + array[i][2], id: array[i][0] };
                }
                getSkladnikAddRecipe(array).then(function () {
                    for (var i = 0 ; i < array.length ; i++) {
                        ingridientArray[i] = { name: array[i][0], id: array[i][1] };
                    }
                    WinJS.Navigation.navigate(link.href);
                })
            })
        }
        else {
            WinJS.Navigation.navigate(link.href);
        }
    }
})();

