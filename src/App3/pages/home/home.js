(function () {
    "use strict";

    function loadRecipeGallery(arg) {
        arg = $(this).data('arg');
        console.log(arg);
        var array = [];
        getOnePrzepis( arg , array).then(function () {
            WinJS.Navigation.navigate( "/pages/recipe/recipe.html" , array );
        })
    }

    WinJS.UI.Pages.define("/pages/home/home.html", {
        ready: function (element, options) {
           
            //load gallery:
            var array = [];
            
            getPrzepisyMax6only(array).then(function () { //array[i][0] - nazwa, array[i][1] - zdjecie.jpg
                var container = '';
                for (var i = 0 ; i < 3 && i < array.length ; i++) {
                    container += '<li><img id="galleryimg' + i.toString() + '" data-arg="' + array[i][0] + '" src="' + "/images/" + array[i][1] + '" title="' + array[i][0] + '" /></li>';
                }
                document.getElementById("1rzad").innerHTML = window.toStaticHTML(container);
                
                container = '';
                for (var i = 3 ; i < 6 && i < array.length ; i++) {
                    container += '<li><img id="galleryimg' + i.toString() + '" data-arg="' + array[i][0] + '" src="' + "/images/" + array[i][1] + '" title="' + array[i][0] + '" /></li>';
                }
                document.getElementById("2rzad").innerHTML = window.toStaticHTML(container);
                
                for (var i = 0 ; i < 6 && i < array.length ; i++) {
                    document.querySelector('#galleryimg' + i.toString()).onclick = loadRecipeGallery;
                }
                
            })
            //gallery is loaded
            
           WinJS.Utilities.query("a").listen("click", anchorHandler, false);
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
                loadRecipiesList(array).then(function(){
                    WinJS.Navigation.navigate(link.href, array);
                })
            })

        }
        else {
            WinJS.Navigation.navigate(link.href);
        }
    }

   
})();
