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
        },
        unload: function () {
            // TODO: Respond to navigations away from this page.
        }
    });
   
   
})();
