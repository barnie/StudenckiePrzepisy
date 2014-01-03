
//dostaje w options tablice z kategoriami jakie ma wyswietlic [i][0] -id [i][1] - nazwa 

(function () {
    "use strict";

    WinJS.UI.Pages.define("/pages/categories/categories.html", {
        // This function is called whenever a user navigates to this page. It
        // populates the page elements with the app's data.
        ready: function (element, options) {
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
            LayoutContainer.prototype.CreateElementContent = function (pictureUrl, onClickFunctionName, imageCss, catName, catId) {
                var content = '';

                var imgPath = "/images/"; //TU NALEZY PODAC SCIEZKE DO OBRAZKOW ZAMIAST "images/"
                content += '<img src="' + imgPath + pictureUrl + '" class="' + imageCss + '" onclick="' + onClickFunctionName + '(' + catId.toString() + ')' + '"></img>';
                content += '<span class="catTitle">' + catName + '</span>';

                return content;
            }
            //script:

                //date prepare:
                var categories = options;
                console.log("categories=" + categories);
                console.log("options.length=" + options.length);
                var count = options.length;
                var picturesPaths = [];
                getRandom(picturesPaths).then(function () {
                    
                        //date prepared, start script:
                        var layout = new LayoutContainer(count, 'myTabCss', 'myRowCss', 'myCellCss');
                        var table = layout.CreateContainer();

                        if (count < 3)
                            document.getElementById('myDiv').innerHTML = window.toStaticHTML('<br><br>' + table.toString());
                        else
                            document.getElementById('myDiv').innerHTML = window.toStaticHTML(table.toString());
                        var prefix = 'first';
                        var j = 0;
                        for (var i = 0; i < count; i++) {
                            console.log(i);
                            console.log(picturesPaths[i][1]);
                            var image = layout.CreateElementContent(picturesPaths[i][1], 'openWindowWithRepice', 'myImageCss', categories[i][1], categories[i][0]);
                            if (j == layout.width) {
                                j = 0;
                                prefix = "second";
                            }

                            document.getElementById(prefix + j.toString()).innerHTML = window.toStaticHTML(image.toString());
                            j++;
                        }

                })
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
