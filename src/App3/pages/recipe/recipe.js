// For an introduction to the Page Control template, see the following documentation:
// http://go.microsoft.com/fwlink/?LinkId=232511
(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/recipe/recipe.html", {
        // This function is called whenever a user navigates to this page. It
        // populates the page elements with the app's data.
        ready: function (element, options) {
            // TODO: Initialize the page here.
            //options to przekazany parametr ze strony wywolujacej (parenta)
            document.getElementById("category").innerHTML = options[1];
            document.getElementById("name").innerHTML = options[2];
            document.getElementById("img_container").innerHTML = window.toStaticHTML("<img src=" + MyGlobals.imagesPath + options[4] + " />");
            document.getElementById("description").innerHTML = options[3];
            //skladniki:
            var container = "<ul>";
            for (var i = 5; i < options.length ; i++) {
                if( options[i][1] == null || options[i][1] == 0 )
                    container += "<li>" + options[i][0] + " - " + options[i][2] + "</li>";
                else
                    container += "<li>" + options[i][0] + " - " + options[i][1] + " " + options[i][2] + "</li>";
            }
            container += " </ul>";
            document.getElementById("list").innerHTML = window.toStaticHTML(container);
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
