(function () {
    "use strict"
    
});

function LoadThatFuckingShit()
{
    var tempCategory = new Array();
    getKategorie(tempCategory).then(function () {
        var categoryArray = new Array();
        for (var i = 0; i < tempCategory.length; i++) {
            categoryArray[i] = { title: tempCategory[i][1], picture: "images/" + tempCategory[i][2], id: tempCategory[i][0] };
        }

        var cat = new WinJS.Binding.List(categoryArray);
        var publicMembers =
        {
            catList: cat
        };
        WinJS.Namespace.define("DataExample1", publicMembers);
    });

    var tempIngridients = new Array();
    getSkladnik(tempIngridients).then(function () {
        var ingridientArray = new Array();
        for (var i = 0; i < tempIngridients.length; i++) {
            ingridientArray[i] = { name: tempIngridients[i][1], id: tempIngridients[i][0] };
        }

        var ing = new WinJS.Binding.List(ingridientArray);
        var publicMembers =
        {
            ingList: ing
        };
        WinJS.Namespace.define("DataExample2", publicMembers);
    });
}
