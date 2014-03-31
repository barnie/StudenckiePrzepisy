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
            var array = [];
            getKategorie(array).then(function () {
                WinJS.Navigation.navigate("/pages/categories/categories.html", array);
            })
            
           //WinJS.Utilities.query("a").listen("click", anchorHandler, false);
        },
        unload: function () {
            // TODO: Respond to navigations away from this page.
        }
    });
    /*
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
    */
   
})();
