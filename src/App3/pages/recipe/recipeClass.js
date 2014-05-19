var ComInf = { //interface, command pattern:
    execute: function (data) { } 
};

function Recipe() { //singleton
   if (arguments.callee._singletonInstance)
        return arguments.callee._singletonInstance;
    arguments.callee._singletonInstance = this;
    this.Display = function(){
        Display.prototype.execute(this.data);
    }
    this.Delete = function () {
        Delete.prototype.execute(document.getElementById("name").innerHTML);
    }
    this.SetValue = function (dataArg) { //Dependency Injection
        this.data = dataArg;
    }
}

var Display = function () { }; //constructor
Display.prototype = Object.create(ComInf); //extend ComInfo for Display
var Delete = function () { };
Delete.prototype = Object.create(ComInf); //extend ComInfo for Delete

Delete.prototype.execute = function (id) {
    removePrzepis(id);
    WinJS.Navigation.navigate("/pages/delete/delete_confirm.html"); //strona z potwierdzeniem usuniecia
}
Display.prototype.execute = function (options) {
    document.getElementById("recipeContainer").style.height = window.screen.availHeight * 1.0 + "px"; //fix issue: flicker of recipe's height  after use bottom menu

    document.getElementById("category").innerHTML = options[1];
    document.getElementById("name").innerHTML = options[2];
    document.getElementById("description").innerHTML = options[3];

    Windows.Storage.ApplicationData.current.localFolder.getFileAsync(options[4]).done(function (file) {
        var imgHandler = document.createElement("img");
        imgHandler.id = "myImgHandler";
        var imageBlob = URL.createObjectURL(file);
        imgHandler.src = imageBlob;
        URL.revokeObjectURL(imageBlob);
        document.getElementById("img_container").appendChild(imgHandler);
    },
    function () {
        var imgHandler = document.createElement("img");
        imgHandler.id = "myImgHandler";
        imgHandler.src = '/images/' + options[4];
        document.getElementById("img_container").appendChild(imgHandler);
    });

    var container = "<ul>";
    for (var i = 5; i < options.length ; i++) {
        if ((options[i][1] == null || options[i][1] == 0) && (options[i][2] == null || options[i][2] == 0))
            container += "<li>" + options[i][0] + "</li>";
        else if (options[i][1] == null || options[i][1] == 0)
            container += "<li>" + options[i][0] + " - " + options[i][2] + "</li>";
        else
            container += "<li>" + options[i][0] + " - " + options[i][1] + " " + options[i][2] + "</li>";
    }
    container += " </ul>";
    document.getElementById("list").innerHTML = window.toStaticHTML(container);
}
