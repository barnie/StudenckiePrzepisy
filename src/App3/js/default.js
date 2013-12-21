﻿// For an introduction to the Navigation template, see the following documentation:
// http://go.microsoft.com/fwlink/?LinkId=232506
(function () {
    "use strict";

    WinJS.Binding.optimizeBindingReferences = true;

    var app = WinJS.Application;
    var activation = Windows.ApplicationModel.Activation;
    var nav = WinJS.Navigation;

    app.addEventListener("activated", function (args) {
        if (args.detail.kind === activation.ActivationKind.launch) {
            if (args.detail.previousExecutionState !== activation.ApplicationExecutionState.terminated) {
                // TODO: This application has been newly launched. Initialize
                // your application here.
            } else {
                // TODO: This application has been reactivated from suspension.
                // Restore application state here.
            }

            if (app.sessionState.history) {
                nav.history = app.sessionState.history;
            }
            args.setPromise(WinJS.UI.processAll().then(function () {
                if (nav.location) {
                    nav.history.current.initialPlaceholder = true;
                    return nav.navigate(nav.location, nav.state);
                } else {
                    return nav.navigate(Application.navigator.home);
                }
            }));
        }
    });

    function buttonClickHandler(eventInfo) {
        // that button test a database only :) Cool and chill nigga!
        //createDB();
        /*  var tab = [];
          tab[0] = new Array(); tab[1] = new Array();
          tab[0][0] = 1; tab[0][1] = 'szklanka'; tab[0][2] = 2;
          tab[1][0] = 2; tab[1][1] = 'lyzka'; tab[1][2] = 3;
           addPrzepis(1,'aaa','xxx','img',tab) */
        /* var array = [];
         getKategorie(array).then(function () {
             for (var i = 0; i < array.length; i++)
                 console.log(array[i]);
         })*/
        /*var array = [];
        getRandom(array).done(function () {
            for (var i = 0; i < array.length; i++)
                console.log(array[i]);
        })*/
        console.log('xD');
        var array = [];
        getPrzepis_poNazwie(array).then(function () {
            for (var i = 0; i < array.length; i++)
                console.log(array[i])
        })
    }


    app.oncheckpoint = function (args) {
        app.sessionState.history = nav.history;
    };

    app.onready = function () {
        //     WinJS.Utilities.query("a").listen("click", anchorHandler, false);

        var button1 = document.getElementById("klik");
        button1.addEventListener("click", buttonClickHandler, false);
    }

    app.start();
})();
