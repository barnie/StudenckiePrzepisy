﻿// For an introduction to the Blank template, see the following documentation:
// http://go.microsoft.com/fwlink/?LinkId=232509

(function () {
    "use strict";

    WinJS.Binding.optimizeBindingReferences = true;

    var app = WinJS.Application;
    var activation = Windows.ApplicationModel.Activation;

    app.onactivated = function (args) {
        if (args.detail.kind === activation.ActivationKind.launch) {
            if (args.detail.previousExecutionState !== activation.ApplicationExecutionState.terminated) {
                // TODO: This application has been newly launched. Initialize
                // your application here.
            } else {
                // TODO: This application has been reactivated from suspension.
                // Restore application state here.
            }
            args.setPromise(WinJS.UI.processAll().done(function () {
                var button1 = document.getElementById("klik");
                button1.addEventListener("click", buttonClickHandler, false);
            })
            );
        }
    };

    function buttonClickHandler(eventInfo) {
        // that button test a database only :) Cool and chill nigga!
         //createDB();
      /*  var tab = [];
        tab[0] = new Array(); tab[1] = new Array();
        tab[0][0] = 1; tab[0][1] = 'szklanka'; tab[0][2] = 2;
        tab[1][0] = 2; tab[1][1] = 'lyzka'; tab[1][2] = 3;
         addPrzepis(1,'aaa','xxx','img',tab) */
        var array = [];
        var z = getKategorie(array).done(function () {
            console.log("AAA");
        }, function (e) {
            console.log(e.message);
        });
        
        
    }

    app.oncheckpoint = function (args) {
        // TODO: This application is about to be suspended. Save any state
        // that needs to persist across suspensions here. You might use the
        // WinJS.Application.sessionState object, which is automatically
        // saved and restored across suspension. If you need to complete an
        // asynchronous operation before your application is suspended, call
        // args.setPromise().
    };

    // App bar initialization.
    document.addEventListener("DOMContentLoaded", function () {
        WinJS.UI.processAll();
    }, false);
    

    app.start();
})();
