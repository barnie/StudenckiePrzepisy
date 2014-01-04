(function () {
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

    var page = WinJS.UI.Pages.define("/pages/search/search.html", {
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
        var found = false;
        for(var i = 0, len = suggestionList.length; i < len; i++)
        {
            if(suggestionList[i].toLowerCase() === queryText.toLowerCase())
            {
                found = true;
                break;
            }
        }
        if(found)
        {
            var myArray = new Array();
            getOnePrzepis(queryText, myArray).then(function ()
            {
                WinJS.Navigation.navigate('/pages/recipe/recipe.html', myArray);
            });
        }
        else
        {
            WinJS.Navigation.navigate('/pages/search/searchFailed.html');
        }
    }
})();