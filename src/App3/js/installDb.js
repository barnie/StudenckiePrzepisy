Windows.Storage.ApplicationData.current.localFolder.getFileAsync("przepisy_db.sqlite").then(function (file) {
        console.log("DB arleady exsists");
},
            function () {
                console.log("DB not exsists");
                createDB();
            }
);
