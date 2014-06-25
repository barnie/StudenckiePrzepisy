(function () {
    "use strict";

    var cat = new WinJS.Binding.List(categoryArray);
    var ing = new WinJS.Binding.List(ingridientArray);
  
    var publicMembers =
        {
            catList: cat,
            ingList: ing
        };
    WinJS.Namespace.define("DataExample", publicMembers);
})();