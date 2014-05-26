// For an introduction to the Page Control template, see the following documentation:
// http://go.microsoft.com/fwlink/?LinkId=232511
(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/delete/delete_confirm.html", {
        // This function is called whenever a user navigates to this page. It
        // populates the page elements with the app's data.
        ready: function (element, options) {
            // TODO: Initialize the page here.
            function goToHomeBack() {
                var array = [];
                getKategorie(array).then(function () {
                    WinJS.Navigation.navigate("/pages/categories/categories.html", array);
                })
            }

            var homeBack = document.getElementById("homeBack");
            homeBack.addEventListener("click", goToHomeBack, false);
        },

        unload: function () {
            // TODO: Respond to navigations away from this page.
        },

        updateLayout: function (element) {
            /// <param name="element" domElement="true" />

            // TODO: Respond to changes in layout.
        }
    });
})();
