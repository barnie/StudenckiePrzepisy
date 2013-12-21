(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/home/home.html", {
        ready: function (element, options) {
             document.querySelector("#cat").onclick = function (args) { //button
                WinJS.Navigation.navigate("/pages/recipe/recipe.html", "smigam z home jooo");
            };

           WinJS.Utilities.query("a").listen("click", anchorHandler, false);
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
        else {
            var array = [];
            getOnePrzepis("ala", array).then(function(){
                WinJS.Navigation.navigate(link.href);
            })
        }
    }

   
})();
