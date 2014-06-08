﻿(function () {
    "use strict";
    var suggestionList = ["Pizza Wegetariańska", "Sernik z musem truskawkowym", "Warstwowa sałatka z tuńczykiem", "Grzene wino z pomarańczami", "Szybkie pożywne śniadanie",
        "Super lekkie kotlety z kaszy jaglanej", "Pig Steak", "Ciasto bardzo marchewkowe", "Jajecznica na boczq", "Sernik", "Tarta z kremem adwokatowym i owocami",
        "Pyszny domowy razowiec", "Szybki sernik", "Melonowy jerzyk", "Zupa meksykańska", "Sorbet truskawkowy", "Sałatka z brokuły", "Wiosenna sałatka",
        "Lody waniliowe z migdałami", "Ciasto truskawkowe z kruszonką", "Krem pomidorowy z mozarellą i bazylią", "Malibu Milk", "Spaghetti bolognese",
        "Suszone pomidory w oleju z przyprawami", "Mus czekoladowy", "Zapiekanka ziemniaczana z kurczakiem", "Czekoladowe muffinki", "Deser malinowo-biszkoptowy",
        "Naleśniki ze szpinakiem i sosem grzybowym", "Galaretkowe piwo", "Zupa og&oacute;rkowa z grzankami", "Whisky Sour", "Donut z polewą czekoladową", "Babka wielkanocna",
        "Omlet Otrębowy", "Omlet ze szpinakiem", "Dietetyczne naleśniki", "Wianek wielkanocny", "Mus czekoladowy z marcepanowymi jajkami", "Naturalne pisanki cebulowe",
        "Tort Szwarcwaldzki", "Panna fit cotta", "Ciasto Cytrynowe", "Kuleczki czekoladowe", "Tymbaliki z kurczaka w galarecie", "Irish coffee", "Panna cotta",
        "Dżem z kiwi i jabłek", "Smażony łosoś z sałatą lodową", "Zupa krem z cukini"];

    var page = WinJS.UI.Pages.define("/pages/search/advancedSearch.html", {
        ready: function (element, options) {
            var searchBox = element.querySelector("#searchBoxId");
            searchBox.addEventListener("suggestionsrequested", suggestionsRequestedHandler);
            searchBox.addEventListener("querysubmitted", querySubmittedHandler);
        }
    });

    function suggestionsRequestedHandler(eventObject) {
        var queryText = eventObject.detail.queryText,
            query = queryText.toLowerCase(),
            suggestionCollection = eventObject.detail.searchSuggestionCollection;
        if (queryText.length > 0) {
            for (var i = 0, len = suggestionList.length; i < len; i++) {
                if (suggestionList[i].substr(0, query.length).toLowerCase() === query) {
                    suggestionCollection.appendQuerySuggestion(suggestionList[i]);
                }
            }
        }
    }

    function querySubmittedHandler(eventObject) {
        var queryText = eventObject.detail.queryText;
        var myArray = new Array();
        var categories = new Array();
        var ingridines = new Array();

        document.getElementById("listaKategorii").winControl.selection.getItems().done(function (items) {
            for (var i = 0; i < items.length; i++) {
                categories[i] = items[i].data.id;
            }
        });

        document.getElementById("mozliweSkladniki").winControl.selection.getItems().done(function (items) {
            for (var i = 0; i < items.length; i++) {
                ingridines[i] = items[i].data.id;
            }
        });

        if (Settings.getFrom != 'ws') {
            findPrzepis(categories, ingridines, myArray).then(function () {
                var tempArray = new Array();
                var j = 0;
                for (var i = 0; i < myArray.length; i++) {
                    if (myArray[i][1].toLowerCase().substr(0, queryText.length) === queryText) {
                        tempArray[j] = myArray[i];
                        j++;
                    }
                }
                if (j == 0)
                    WinJS.Navigation.navigate('/pages/search/searchFailed.html');
                else {
                    loadRecipiesList(tempArray).then(function () {
                        WinJS.Navigation.navigate("pages/list_recipes/list_recipes.html", tempArray);
                    })
                }


            }
            );
        }
        else {
            WebServiceHandler.findPrzepis(categories, ingridines, myArray).then(function () {
                var tempArray = new Array();
                var j = 0;
                for (var i = 0; i < myArray.length; i++) {
                    if (myArray[i][1].toLowerCase().substr(0, queryText.length) === queryText) {
                        tempArray[j] = myArray[i];
                        j++;
                    }
                }
                if (j == 0)
                    WinJS.Navigation.navigate('/pages/search/searchFailed.html');
                else {
                    loadRecipiesList(tempArray);
                    WinJS.Navigation.navigate("pages/list_recipes/list_recipes.html", tempArray);
                }


            }
            );
        }
    }

})();