(function () {
    "use strict";

    var myData = [];
    for (var i = 0 ; i < MyGlobals.recipies.length ; i++) {
        myData[i] = { title: MyGlobals.recipies[i][1] };
    }
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




})();


