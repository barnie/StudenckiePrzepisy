


(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/list_recipes/list_recipes.html", {
        // This function is called whenever a user navigates to this page. It
        // populates the page elements with the app's data.
        ready: function (element, options) {
            var listView = element.querySelector('#groupedListView').winControl;
            if (options.length == 0) {
                document.getElementById("empty").style.display = 'inline';
            }
            else {
                document.getElementById("empty").style.display = 'none';
            }
            // Notify the ListView to calculate its layout
            // listView.forceLayout();

            function itemInvokedHandler(eventObject) {
                eventObject.detail.itemPromise.done(function (invokedItem) {

                    // Access item data from the itemPromise
                    var array = [];
                    if (Settings.getFrom != 'ws') {
                        getOnePrzepis(invokedItem.data.title, array).then(function () {
                            WinJS.Navigation.navigate("/pages/recipe/recipe.html", array); //idziemy do strony z przepisem
                        })
                    }
                    else {
                        var rightArray = invokedItem.data.array;
                        if (rightArray[0][1]) { //to avoid double change
                            //remember values from old possitions
                            var title = rightArray[1];
                            var description = rightArray[2];
                            var picture = rightArray[3];
                            var category = rightArray[4];
                            //set correct possitions for recipe
                            rightArray[1] = category;
                            rightArray[2] = title;
                            rightArray[3] = description;
                            rightArray[4] = picture;
                            rightArray[0][1] = false;
                            //get Ingridients names
                            var ingAr = new Array();
                            var itIng = 5;
                            for (var i = 0 ; itIng < rightArray.length ; i++) {
                                ingAr[i] = rightArray[itIng][0];
                                itIng++;
                            }

                            if (ingAr.length > 0) {
                                getNameOfSkladniki(rightArray, ingAr).then(function () {
                                    WinJS.Navigation.navigate("/pages/recipe/recipe.html", rightArray); //idziemy do strony z przepisem
                                })
                            }
                            else {
                                WinJS.Navigation.navigate("/pages/recipe/recipe.html", rightArray); //idziemy do strony z przepisem
                            }
                        }
                        else {
                            WinJS.Navigation.navigate("/pages/recipe/recipe.html", rightArray); //idziemy do strony z przepisem
                        }

                        
                    }
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
