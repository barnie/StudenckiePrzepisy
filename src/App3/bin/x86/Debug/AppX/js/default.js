// For an introduction to the Blank template, see the following documentation:
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
        var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\db.sqlite';
        // wymagane wlaczenie podgladu ukrytych folderow by zobaczyc plik bazy
        //baze przechowuje w Uzytkownicy/Nazwa_uzytkownika/Appdata/Local/Packages/nazwa_paczki/LocalState lub LocalCache ;p
        SQLite3JS.openAsync(dbPath)
        .then(function (db) {
            return db.runAsync('CREATE TABLE Item (name TEXT, price REAL, id INT PRIMARY KEY)')
            .then(function () {
                return db.runAsync('INSERT INTO Item (name, price, id) VALUES (?, ?, ?)', ['Mango', 4.6, 123]);
            })
            .then(function () {
                return db.eachAsync('SELECT * FROM Item', function (row) {
                    console.log('Get a ' + row.name + ' for $' + row.price);
                });
            })
            .then(function () {
                db.close();
            });
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

    app.start();
})();
