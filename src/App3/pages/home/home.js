(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/home/home.html", {
        ready: function (element, options) {
            console.log("home");

            document.querySelector("#cat").onclick = function (args) { //button
                WinJS.Navigation.navigate("/pages/recipe/recipe.html", "smigam z home jooo");
            };

           WinJS.Utilities.query("a").listen("click", anchorHandler, false);
        }
    });

    function anchorHandler(eventInfo) { //jak sie w linka kliknie
        eventInfo.preventDefault();
        var link = eventInfo.target;

        WinJS.Navigation.navigate(link.href, "z home");
    }

   
})();
