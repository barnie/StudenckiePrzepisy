(function () {
    "use strict";
    function changeToWs() {
        if (Settings.getFrom != 'ws') {
            Settings.getFrom = 'ws';
            document.getElementById('imgDbBut').style.display = "inline";
            document.getElementById('imgWsBut').style.display = "none";
            Windows.Storage.ApplicationData.current.localFolder.getFileAsync("settings.txt").done(function (file) {
                Windows.Storage.FileIO.writeTextAsync(file, 'ws');
            });
            WinJS.Navigation.navigate("pages/settings/settings.html"); //refresh
        }
    }
    function changeToDb() {
        if (Settings.getFrom != 'db') {
            Settings.getFrom = 'db';
            document.getElementById('imgWsBut').style.display = "inline";
            document.getElementById('imgDbBut').style.display = "none";
            Windows.Storage.ApplicationData.current.localFolder.getFileAsync("settings.txt").done(function (file) {
                Windows.Storage.FileIO.writeTextAsync(file, 'db');
            });
            WinJS.Navigation.navigate("pages/settings/settings.html"); //refresh
        }
    }
    function goBackHome() {
        var array = [];
        getKategorie(array).then(function () {
            WinJS.Navigation.navigate("pages/categories/categories.html", array);
        })

    }
    WinJS.UI.Pages.define("/pages/settings/settings.html", {
        ready: function (element, options) {
            if (Settings.getFrom == 'ws') {
                document.getElementById('imgDbBut').style.display = "none";
            }
            else {
                document.getElementById('imgWsBut').style.display = "none";
            }
            var goBack = document.getElementById("goBackBut"); //to avoid problem with back after change set
            var butWs = document.getElementById("wsBut");
            var butDb = document.getElementById("dbBut");
            butWs.addEventListener("click", changeToWs, false);
            butDb.addEventListener("click", changeToDb, false);
            goBack.addEventListener("click", goBackHome, false);
        },
        unload: function () {
            // TODO: Respond to navigations away from this page.
        },
    });
   
   
})();
