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
            var recipe = new Recipe();
            recipe.SetValue(options); //Dependency Injection
            recipe.Display();
            var deleteRecipe = document.getElementById("buttonDel");
            deleteRecipe.addEventListener("click", recipe.Delete, false);
            document.getElementById("buttonDel").style.display = "inline";
            //koniec ustawiania usuwania
        },

        unload: function () {
            // TODO: Respond to navigations away from this page.
            document.getElementById("buttonDel").style.display = "none";
        },

        updateLayout: function (element, viewState, lastViewState) {
            /// <param name="element" domElement="true" />

            // TODO: Respond to changes in viewState.
        }
    });
})();
