


(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/list_recipes/list_recipes.html", {
        // This function is called whenever a user navigates to this page. It
        // populates the page elements with the app's data.
        ready: function (element, options) {
            console.log(options);
            function LayoutContainer(size, tableCss, rowCss, cellCss) {
                this.width = Math.ceil(size / 2.0);
                this.tableCss = tableCss;
                this.rowCss = rowCss;
                this.cellCss = cellCss;
                this.cellId = 0;
                this.rowId = 'first';
            }

            // tworzenie tabeli przechowuj¹cej przepisy
            LayoutContainer.prototype.CreateContainer = function () {
                var container = '';

                container += '<table id="container" class="' + this.tableCss + '"><tr id="first" class="' + this.rowCss + '">';

                for (var i = 0; i < this.width; i++) {
                    container += '<td id="first' + i.toString() + '" class="' + this.cellCss + '"></td>';
                }

                container += '</tr><tr id="second" class="' + this.rowCss + '">';

                for (var i = 0; i < this.width; i++) {
                    container += '<td id="second' + i.toString() + '" class="' + this.cellCss + '"></td>';
                }

                container += '</tr></table>';

                return container;
            }

            // funkcja definiowana przez onClickFunctionName (np. openRepice) musi przyjmowac tylko jeden argument który jest identyfikatorem przepisu (np. openRepice(repiceId))
            LayoutContainer.prototype.CreateElementContent = function (pictureUrl, onClickFunctionName, imageCss, repiceId) {
                var content = '';

                content += '<img src="' + pictureUrl + '" class="' + imageCss + '" onclick="' + onClickFunctionName + '(' + repiceId.toString() + ')' + '"></img>';

                return content;
            }

            var count = 10;
            var layout = new LayoutContainer(count, 'myTabCss', 'myRowCss', 'myCellCss');
            var table = layout.CreateContainer();
            //przykladowe dane (powinny byc z db pobierane)
            var picturesPaths = ["images/1.jpg", "images/3.jpg", "images/pobrane.jpg",
                "images/4.jpg", "images/5.jpg", "images/6.jpg", "images/cycuszki.jpg", "images/7.jpg",
                "images/8.jpg", "images/10.jpg"];
            var repicesIds = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

            if (count < 3)
                document.getElementById('myDiv').innerHTML = window.toStaticHTML('<br><br>' + table.toString());
            else
                document.getElementById('myDiv').innerHTML = window.toStaticHTML(table.toString());
            var prefix = 'first';
            var j = 0;
            for (var i = 0; i < count; i++) {
                var image = layout.CreateElementContent(picturesPaths[i], 'openWindowWithRepice', 'myImageCss', repicesIds[i]);
                if (j == layout.width) {
                    j = 0;
                    prefix = "second";
                }

                document.getElementById(prefix + j.toString()).innerHTML = window.toStaticHTML(image.toString());
                j++;
            }


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
