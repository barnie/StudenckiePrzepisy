


(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/list_recipes/list_recipes.html", {
        // This function is called whenever a user navigates to this page. It
        // populates the page elements with the app's data.
        ready: function (element, options) {
            var listView = element.querySelector('#groupedListView').winControl;

            // Notify the ListView to calculate its layout
            // listView.forceLayout();

            function itemInvokedHandler(eventObject) {
                eventObject.detail.itemPromise.done(function (invokedItem) {

                    // Access item data from the itemPromise
                    var array = [];
                    getOnePrzepis(invokedItem.data.title, array).then(function () {
                        WinJS.Navigation.navigate("/pages/recipe/recipe.html", array); //idziemy do strony z przepisem
                    })
                });
            }

            listView.addEventListener("iteminvoked", itemInvokedHandler, false);

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
