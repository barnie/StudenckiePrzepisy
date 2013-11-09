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
        var md = new Windows.UI.Popups.MessageDialog("Hello World!");
        (new Windows.UI.Popups.MessageDialog("Test", "Button testujacy klikanie mozna wypieprzyc go z layoutu")).showAsync().done();

        var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\db.sqlite',
      Package = Windows.ApplicationModel.Package;

        // Wait for DOM to be ready
        WinJS.Utilities.ready().then(function () {
            return SQLite3JS.openAsync(dbPath);
        }).then(function (db) {
            return db.runAsync('CREATE TABLE IF NOT EXISTS images (id INT PRIMARY KEY, image BLOB)')
            .then(function () {
                // Get an image to insert into the database as blob
                return Package.current.installedLocation.getFileAsync("images\\logo.png");
            }).then(function (file) {
                return Windows.Storage.FileIO.readBufferAsync(file);
            }).then(function (buffer) {
                return db.runAsync('INSERT INTO images (image) VALUES (?)', [buffer]);
            }).then(function () {
                var div;
                return db.eachAsync('SELECT image FROM images', function (row) {
                    div = document.createElement("img");
                    div.src = 'data:image/png;base64,' + row.image;
                    document.body.appendChild(div);
                });
            }).then(function () {
                return db.runAsync("DROP TABLE images");
            }).then(function () {
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
