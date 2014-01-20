     (function () {
         "use strict";

         WinJS.UI.Pages.define("/Example/BindingPicturesExample.html", { // definicja nazwy strony html
             ready: function (element, options) {

                 // TODO 1: Załaduj dane do zmiennych w tym miejscu

                 // Te zmienne muszą zostać zainicjalizowane przez ciebie z bazy danych obecnie to tzw. dummy data z ktorem tez bedzie dzialac
                 var count = 3;
                 var picturesPaths = ["images/1.jpg", "images/2.jpg", "images/3.jpg"];
                 var repicesIds = ["1", "2", "3"];

                 // TODO 1

                 var layout = new LayoutContainer(count, 'myTableCss', 'myRowCss', 'myCellCss');

                 var table = layout.CreateContainer();

                 document.getElementById('myDiv').innerHTML = window.toStaticHTML(table.toString());

                 var prefix = 'first';
                 var j = 0;
                 var imgTagsIds = new Array();

                 for (var i = 0; i < count; i++) {

                     var image = layout.CreateElementContent(prefix + j.toString(), picturesPaths[i], 'myImageCss', repicesIds[i]);

                     imgTagsIds[i] = prefix + j.toString() + 'img';

                     if (j == layout.width) {
                         j = 0;
                         prefix = 'second';
                     }

                     document.getElementById(prefix + j.toString()).innerHTML = window.toStaticHTML(image.toString());

                     j++;
                 }

                 for(var i=0; i<count; i++)
                 {
                     element.querySelector('#' + imgTagsIds[i]).onclick = this.loadRecipe;
                 }
             },
             loadRecipe : function (arg)
             {
                 arg = $(this).data('arg');
                 var myArray = new Array();
                 getOnePrzepis(arg, myArray).then(function () {
                     if (myArray[2] == undefined) {
                         WinJS.Navigation.navigate('/pages/search/searchFailed.html');
                     }
                     else {
                         WinJS.Navigation.navigate('/pages/recipe/recipe.html', myArray);
                     }
                 });
             },
             unload: function () {
                 // TODO: Respond to navigations away from this page.
             }
         });

     })();