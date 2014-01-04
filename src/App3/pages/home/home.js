(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/home/home.html", {
        ready: function (element, options) {

           WinJS.Utilities.query("a").listen("click", anchorHandler, false);
        },
        unload: function () {
            // TODO: Respond to navigations away from this page.
        }
    });

    function subOptionMenu(link) { //z linku bierze
    }

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
                loadRecipiesList( array ); //ladujemy liste przepisow
                WinJS.Navigation.navigate(link.href , array ); //tu tez podajemy array zeby wyswietlic ze pusto jc
            })

        }
        else {
            var array = [];
            getOnePrzepis("ala", array).then(function () {
                WinJS.Navigation.navigate(link.href, array);
            })
        }
    }

   
})();
