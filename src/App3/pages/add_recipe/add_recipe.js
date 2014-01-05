(function () {
    "use strict";
    var fileObj;
    var page = WinJS.UI.Pages.define("/pages/add_recipe/add_recipe.html", {
        ready: function (element, options) {
            var button1 = document.getElementById("selectPic");
            button1.addEventListener("click", this.OpenPictureBrowser, false);
            var button2 = document.getElementById("saveit");
            button2.addEventListener("click", this.SaveRecipe, false);
            var button3 = document.getElementById("removeSkladnikbtn");
            button3.addEventListener("click", this.DeleteIng, false);
            var button4 = document.getElementById("addSkladnikbtn");
            button4.addEventListener("click", this.AddIng, false);
        },
        OpenPictureBrowser: function () {
            var openPicker = new Windows.Storage.Pickers.FileOpenPicker();
            openPicker.viewMode = Windows.Storage.Pickers.PickerViewMode.thumbnail;
            openPicker.suggestedStartLocation = Windows.Storage.Pickers.PickerLocationId.picturesLibrary;
            openPicker.fileTypeFilter.replaceAll([".png", ".jpg", ".jpeg"]);
            openPicker.pickMultipleFilesAsync().then(function (files) {
                if (files) {
                    fileObj = files;
                    var imageBlob = URL.createObjectURL(files[0]);
                    document.getElementById("imgHolder").src = imageBlob;
                    document.getElementById("imgHolder").alt = files[0].path;
                    URL.revokeObjectURL(imageBlob);
                }
            });
        },
        SaveRecipe: function () {
            
            var obj = document.getElementById("listaKategorii").winControl;
            var categoryid;
            obj.selection.getItems().done(function (items) {
                if (items.length > 0) {
                    var item = items[0];
                    categoryid = item.data.id;
                }
            });

            var obj2 = document.getElementById("repiceName");
            var nazwa = obj2.value;
            var obj3 = document.getElementById("repiceGuide");
            var opis = obj3.value;
            var fileName = "";
 
            if (fileObj != undefined) {
                var dataPackage = new Windows.ApplicationModel.DataTransfer.DataPackage();
                dataPackage.setStorageItems(fileObj);
                dataPackage.requestedOperation = Windows.ApplicationModel.DataTransfer.DataPackageOperation.copy;
                Windows.ApplicationModel.DataTransfer.Clipboard.setContent(dataPackage);
                var dataPackageView = Windows.ApplicationModel.DataTransfer.Clipboard.getContent();
                if (dataPackageView.contains(Windows.ApplicationModel.DataTransfer.StandardDataFormats.storageItems)) {
                    dataPackageView.getStorageItemsAsync().done(function (storageItems) {
                        var item = storageItems[0];
                        if (item.isOfType(Windows.Storage.StorageItemTypes.file)) {
                            item.copyAsync(Windows.Storage.ApplicationData.current.localFolder, item.name);
                            fileName = item.name;
                        }
                    });
                }
            }
            var skladniki = new Array();

            var obj4 = document.getElementById("listaSkladnikow").winControl;
            obj4.selection.selectAll();
            obj4.selection.getItems().done(function (items) {
                for (var i = 0; i < items.length; i++) {
                    skladniki[i] = new Array();
                    skladniki[i][0] = items[i].data.id;
                    skladniki[i][1] = items[i].data.miara;
                    skladniki[i][2] = items[i].data.ile;
                }
            });

            if (nazwa != undefined && opis != undefined && fileObj != undefined && categoryid != undefined) {
                addPrzepis(categoryid, nazwa, opis, fileName, skladniki);
                WinJS.Navigation.navigate("/pages/add_recipe/add_recipe_succes.html");
            }
            else
            {
                Windows.UI.Popups.MessageDialog("Uzupełnij formularz").showAsync();
            }
        },
        DeleteIng: function ()
        {
            var obj = document.getElementById("listaSkladnikow").winControl;
            obj.selection.getItems().done(function (items) {
                if (items.length > 0) {
                    for (var i = 0; i < items.length; i++) {
                        obj.itemDataSource.remove(items[i].key);
                    }
                }
            });
        },
        AddIng: function ()
        {
            var obj = document.getElementById("listaSkladnikow").winControl;
            var miaraA = document.getElementById("jednostka").value;
            var ileA = parseFloat(document.getElementById("skladnikAmount").value);
            var obj2 = document.getElementById("mozliweSkladniki").winControl;
            obj2.selection.getItems().done(function (items) {
                if (items.length > 0) {
                    var idA = items[0].data.id;
                    var nazwaA = items[0].data.name;
                    obj.itemDataSource.insertAtEnd(idA, { id: idA, ile: ileA, miara: miaraA, nazwa: nazwaA }).done();
                }
            });
        }
    });

})();