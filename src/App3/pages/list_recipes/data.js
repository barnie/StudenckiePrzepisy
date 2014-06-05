var AggrInf = { //interface, interator pattern:
    getIterator: function (dataIn) { }
};

var ItInf = { //interface, interator pattern:
    next: function () { }
};

//ListData class
function ListData() { //singleton pattern
    if (arguments.callee._singletonInstance)
        return arguments.callee._singletonInstance;
    arguments.callee._singletonInstance = this;
    this.setValue = function (dataArg) { //Dependency Injection
        this.data = dataArg;
    }
    this.data = null;
    this.it = null;
};
ListData.prototype = Object.create(AggrInf); //extend AggrInf for ListData

ListData.prototype.getIterator = function () {
    this.it = new ItList(this.data);
    if (this.data.length > 0) return true;
    else return false;
}

//ItList class
var ItList = function (dataIn) {
    this.curIndx = 0;
    this.data = dataIn;
    if (this.data.length < 1) {
        this.title = null;
        this.picture = null;
    }
    else {
        this.title = this.data[this.curIndx][1];
        this.picture = this.data[this.curIndx][3];
    }
};
ItList.prototype = Object.create(ItInf);
ItList.prototype.next = function () {
    if (this.curIndx + 1 < this.data.length) {
        this.curIndx++;
        this.title = this.data[this.curIndx][1];
        this.picture = this.data[this.curIndx][3];
        return true;
    }
    return false; //signal that is the end of collection
}

function loadRecData(i, myData, array) {
    return Windows.Storage.ApplicationData.current.localFolder.getFileAsync(array.it.picture).then(function (file) {
        var varShortTitle;
        if (array.it.title.length > 30)
            varShortTitle = array.it.title.substr(0, 27) + "...";
        else
            varShortTitle = array.it.title;
        myData[myData.length] = { title: array.it.title, shortTitle: varShortTitle, picture: 'url("' + "ms-appdata:///local/" + array.it.picture + '")', size: 'cover' };
        i = array.it.next();
        console.log("i = " + i);
        if (i)
            return loadRecData(i, myData, array);
    },
            function () {
                var varShortTitle;
                if (array.it.title.length > 30)
                    varShortTitle = array.it.title.substr(0, 27) + "...";
                else
                    varShortTitle = array.it.title;
                myData[myData.length] = { title: array.it.title, shortTitle: varShortTitle, picture: 'url("' + "/images/" + array.it.picture + '")', size: 'cover' };
                i = array.it.next();
                console.log("i = " + i);
                if (i)
                    return loadRecData(i, myData, array);
            });
}

function loadRecipiesList(array) {
    "use strict";
    var myData = [];
    var listObj = new ListData();
    listObj.setValue(array);
    var check = listObj.getIterator(); //if false 0 obj in collection
    if (!check) return 0;
    return loadRecData(check, myData, listObj).then(function () { //important - is using to asynch dowload data and chose correct url of img

        // Create a WinJS.Binding.List from the array. 
        var itemsList = new WinJS.Binding.List(myData);

        // Sorts the groups
        function compareGroups(leftKey, rightKey) {
            return leftKey.charCodeAt(0) - rightKey.charCodeAt(0);
        }

        // Returns the group key that an item belongs to
        function getGroupKey(dataItem) {
            return dataItem.title.toUpperCase().charAt(0);
        }

        // Returns the title for a group
        function getGroupData(dataItem) {
            return {
                title: dataItem.title.toUpperCase().charAt(0)
            };
        }

        // Create the groups for the ListView from the item data and the grouping functions
        var groupedItemsList = itemsList.createGrouped(getGroupKey, getGroupData, compareGroups);


        WinJS.Namespace.define("myData",
              {
                  groupedItemsList: groupedItemsList
              });
    })
}
