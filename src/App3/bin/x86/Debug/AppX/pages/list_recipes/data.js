function loadRecipiesList( array ) {
    "use strict";

    var myData = [];
    for (var i = 0 ; i < array.length ; i++) {
        if (array[i][1].length > 18) { //skracanie (tekst...), zeby sie w jednej lini miescilo
            array[i][1] = array[i][1].substring(0, 15) + "...";
        }
        myData[i] = { title: array[i][1], picture: 'url("' + MyGlobals.imagesPath + array[i][3] + '")' }; //picture: MyGlobals.imagesPath + "" + array[i][3]
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




}



