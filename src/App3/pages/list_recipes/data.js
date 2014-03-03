function loadRecData(i, myData, array) {
    return Windows.Storage.ApplicationData.current.localFolder.getFileAsync(array[i][3]).then(function (file) {
        myData[i] = { title: array[i][1], picture: 'url("' + "ms-appdata:///local/" + array[i][3] + '")' };
        i++;
        if (i < array.length)
            return loadRecData(i, myData, array);
    },
            function () {
                myData[i] = { title: array[i][1], picture: 'url("' + "/images/" + array[i][3] + '")' };
                i++;
                if (i < array.length)
                    return loadRecData(i, myData, array);
            });
}

function loadRecipiesList(array) {
    "use strict";
    var myData = [];
    return loadRecData(0, myData, array).then( function(){
        

        //    myData[i] = { title: array[i][1], picture: 'url("' + "ms-appdata:///local/" + array[i][3] + '")' };




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




