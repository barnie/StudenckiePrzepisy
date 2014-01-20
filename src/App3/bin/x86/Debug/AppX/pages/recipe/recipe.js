// For an introduction to the Page Control template, see the following documentation:
// http://go.microsoft.com/fwlink/?LinkId=232511
(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/recipe/recipe.html", {
        // This function is called whenever a user navigates to this page. It
        // populates the page elements with the app's data.
        ready: function (element, options) {
            // TODO: Initialize the page here.
            //options to przekazany parametr ze strony wywolujacej (parenta)
            
            //usuwanie przepisu
            function delRec() {
                removePrzepis(options[2]);
                WinJS.Navigation.navigate("/pages/delete/delete_confirm.html"); //strona z potwierdzeniem usuniecia
                       
            }
            var deleteRecipe = document.getElementById("buttonDel");
            deleteRecipe.addEventListener("click", delRec, false);
            document.getElementById("buttonDel").style.display = "inline";
            //koniec ustawiania usuwania

            document.getElementById("category").innerHTML = options[1];
            console.log("option[1]=" + options);
            document.getElementById("name").innerHTML = options[2];
            //var picturesPath = "/images/"; //TU MUSI BYC INNA SCIEZKA NA KONIEC!

            Windows.Storage.ApplicationData.current.localFolder.getFileAsync(options[4]).done(function (file) {
                var imgHandler = document.createElement("img");
                imgHandler.id = "myImgHandler";
                var imageBlob = URL.createObjectURL(file);
                imgHandler.src = imageBlob;
                URL.revokeObjectURL(imageBlob);
                document.getElementById("img_container").appendChild(imgHandler);
            },
            function () {
                var imgHandler = document.createElement("img");
                imgHandler.id = "myImgHandler";
                imgHandler.src = '/images/' + options[4];
                document.getElementById("img_container").appendChild(imgHandler);
            });
           

            console.log("options[4]=" + options[4]);
            document.getElementById("description").innerHTML = options[3];
            //skladniki:
            var container = "<ul>";
            for (var i = 5; i < options.length ; i++) {
                if( ( options[i][1] == null || options[i][1] == 0 ) && ( options[i][2] == null || options[i][2] == 0 ) )
                    container += "<li>" + options[i][0] + "</li>";
                else if( options[i][1] == null || options[i][1] == 0 )
                    container += "<li>" + options[i][0] + " - " + options[i][2] + "</li>";
                else
                    container += "<li>" + options[i][0] + " - " + options[i][1] + " " + options[i][2] + "</li>";
            }
            container += " </ul>";
            document.getElementById("list").innerHTML = window.toStaticHTML(container);
        },

        unload: function () {
            // TODO: Respond to navigations away from this page.
            document.getElementById("buttonDel").style.display = "none";
        },

        updateLayout: function (element, viewState, lastViewState) {
            /// <param name="element" domElement="true" />

            // TODO: Respond to changes in viewState.
        }
    });
})();
