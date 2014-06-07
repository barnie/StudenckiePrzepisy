WebServiceHandler = {
    defaultCategories: "1!2!3!4!5!6" ,
    userDb: "abis",
    passDb: "siba",
    method: "GetRepiceList",
    wsdlUrl: "http://54.186.231.191:8080/SOAPService/RecipeService?wsdl",
    xmlDoc: new Windows.Data.Xml.Dom.XmlDocument(),
    getDefaultCategories: function(){ //from local db
        var catDb = [];
        this.defaultCategories = getKategorie(catDb).then(function(){
            result = "";
            for( var i = 0 ; i < catDb.length ; i++){
                result += catDb[i][0] + '!';
            }
            console.log(result);
            return result;
        });
    },
    request: function (categories, ingridients)
    {
        if (typeof (ingridients) === 'undefined') ingridients = '';
        
        return '<?xml version="1.0" encoding="utf-8"?>' +
               '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://WebService/">' +
               '<soapenv:Header/>' +
               '<soapenv:Body>' +
               '<web:GetRepiceList>' +
                     '<categories>' + categories + '</categories>' +
                     '<ingridients>' + ingridients + '</ingridients>' +
                     '<user>'+ this.userDb + '</user>' +
                     '<pass>' + this.passDb + '</pass>' +
               '</web:GetRepiceList>' +
               '</soapenv:Body>' +
               '</soapenv:Envelope>';
    },
    getNameOfSkladnik: function(array, nrIng){
        var nameCont = new Array();
        return getNameOfSkladnik(nameCont, array[nrIng][0]).then(function () {
            array[nrIng][0] = nameCont[0];
            console.log("nameCount = " + nameCont[0]);
            if (array.length < nrIng)
                return this.getNameOfSkladnik(array, ++nrIng);
        })
    },
    getSkladniki: function (array, itRec, xmlDoc) {
        var itIng = 5;
        if (itRec == -1) { //if it is only for one recipe
            for (var i = 0 ; i < xmlDoc.childNodes.length ; i++) {
                array[itIng] = new Array();
                array[itIng][0] = xmlDoc.getElementsByTagName('id')[i].innerText;
                array[itIng][1] = xmlDoc.getElementsByTagName('amount')[i].innerText;
                array[itIng][2] = xmlDoc.getElementsByTagName('unit')[i].innerText;
                itIng++;
            }
        }
        else {
            for (var i = 0 ; i < xmlDoc.childNodes.length ; i++) {
                array[itRec][itIng] = new Array();
                array[itRec][itIng][0] = xmlDoc.getElementsByTagName('id')[i].innerText;
                array[itRec][itIng][1] = xmlDoc.getElementsByTagName('amount')[i].innerText;
                array[itRec][itIng][2] = xmlDoc.getElementsByTagName('unit')[i].innerText;
                itIng++;
            }
        }
         
    },
    getSkladnikiForAA: function (array, xmlDoc, listSearchedSkl) {
        var itIng = 5;
        var ammountOfFoundIng = 0;
        var allNeededIng = listSearchedSkl.length;
        for (var i = 0 ; i < xmlDoc.childNodes.length ; i++) {
            var id = xmlDoc.getElementsByTagName('id')[i].innerText;
            var rememberInx = listSearchedSkl.indexOf(parseInt(id))
            if ( rememberInx != -1) {
                ammountOfFoundIng++;
                listSearchedSkl = listSearchedSkl.splice(0, rememberInx).concat(listSearchedSkl.splice(rememberInx + 1, listSearchedSkl.length)); //to not count double ingrds
            };
            array[itIng] = new Array();
            array[itIng][0] = id;
            array[itIng][1] = xmlDoc.getElementsByTagName('amount')[i].innerText;
            array[itIng][2] = xmlDoc.getElementsByTagName('unit')[i].innerText;
            itIng++;
        }
        if (ammountOfFoundIng == allNeededIng) return true;
        else return false;
    }
         
,
getOnePrzepis: function (queryText, array){
    queryText = queryText.toLowerCase();
    if (typeof (idCat) === 'undefined') {
        if (this.defaultCategories == "") throw "Error: defaultCategories is empty";
        idCat = this.defaultCategories;
    }
    return WinJS.xhr({
        type: "post",
        url: this.wsdlUrl,
        data: this.request(idCat),
        headers: {
            "Content-Type": "text/xml; charset=utf-8",
            "SOAPAction": this.method
        }
    }).then(function (msg) {
        var xmlDoc = new Windows.Data.Xml.Dom.XmlDocument();
        xmlDoc.loadXml(msg.responseText); //load xml which include other xml 
        var rightSource = xmlDoc.documentElement.childNodes[0].innerText.replace(/&/g, ''); //remove & for fix problem with loadXml function
        xmlDoc.loadXml(rightSource); //load right xml
        var recipeData = xmlDoc.documentElement;
        for (var i = 0; i < xmlDoc.documentElement.childNodes.length; i++) {
            var title = recipeData.getElementsByTagName('title')[i].innerText;
            if (title.toLowerCase() == queryText) {
                array[0] = recipeData.getElementsByTagName('id')[i].innerText;
                array[1] = recipeData.getElementsByTagName('category')[i].innerText;
                array[2] = title;
                array[3] = recipeData.getElementsByTagName('description')[i].innerText;
                array[4] = recipeData.getElementsByTagName('picture')[i].innerText;
                var ingridientsOfRec = recipeData.getElementsByTagName('ingridients')[i];
                WebServiceHandler.getSkladniki(array, -1, ingridientsOfRec);
            }
        }
    })
},
findPrzepis: function(kategorie,skladniki,array){
    return WinJS.xhr({
        type: "post",
        url: this.wsdlUrl,
        data: this.request(this.defaultCategories),
        headers: {
            "Content-Type": "text/xml; charset=utf-8",
            "SOAPAction": this.method
        }
    }).then(function (msg) {
        var xmlDoc = new Windows.Data.Xml.Dom.XmlDocument();
        xmlDoc.loadXml(msg.responseText); //load xml which include other xml 
        var rightSource = xmlDoc.documentElement.childNodes[0].innerText.replace(/&/g, ''); //remove & for fix problem with loadXml function
        xmlDoc.loadXml(rightSource); //load right xml
        var recipeData = xmlDoc.documentElement;
        var added = 0;
        for (var i = 0; i < xmlDoc.documentElement.childNodes.length; i++) {
            var categoryId = recipeData.getElementsByTagName('categoryId')[i].innerText;
            var ingridientsOfRec = recipeData.getElementsByTagName('ingridients')[i];
            var tempArray = new Array()
            var goodRec = false;
            if (kategorie.length < 1 || kategorie.indexOf(parseInt(categoryId)) != -1) {
                goodRec = WebServiceHandler.getSkladnikiForAA(tempArray, ingridientsOfRec, skladniki);
            }

            if (goodRec) {
                array[added] = new Array();
                array[added][0] = new Array()
                array[added][0][0] = recipeData.getElementsByTagName('id')[i].innerText;
                array[added][0][1] = true; //inform about array for listView (true) or recipe.html (false)
                array[added][1] = recipeData.getElementsByTagName('title')[i].innerText;
                array[added][2] = recipeData.getElementsByTagName('description')[i].innerText;
                array[added][3] = recipeData.getElementsByTagName('picture')[i].innerText;
                array[added][4] = recipeData.getElementsByTagName('category')[i].innerText;
                for (var k = 5 ; k < tempArray.length; k++) array[added][k] = tempArray[k]; //add ingr
                added++;
            }
        }
    })
},
getPrzepisy: function (array, idCat) { //cat. (and ing.) format 1!3!2 WITHOUT names of ingridients (have to use WebServiceHandler.getNameOfSkladniki())
    if (typeof (idCat) === 'undefined') {
        if (this.defaultCategories == "") throw "Error: defaultCategories is empty";
        idCat = this.defaultCategories;
    }
    return WinJS.xhr({
        type: "post",
        url: this.wsdlUrl,
        data: this.request(idCat),
        headers: {
            "Content-Type": "text/xml; charset=utf-8",
            "SOAPAction": this.method
        }
    }).then(function (msg) {
        var xmlDoc = new Windows.Data.Xml.Dom.XmlDocument();
        xmlDoc.loadXml(msg.responseText); //load xml which include other xml 
        var rightSource = xmlDoc.documentElement.childNodes[0].innerText.replace(/&/g, ''); //remove & for fix problem with loadXml function
        rightSource = rightSource.replace(/�/g, 'ó'); //fix problem with display ó
        xmlDoc.loadXml(rightSource); //load right xml
        var recipeData = xmlDoc.documentElement;
        for (var i = 0; i < xmlDoc.documentElement.childNodes.length; i++) {
            array[i] = new Array();
            array[i][0] = new Array();
            array[i][0][0] = recipeData.getElementsByTagName('id')[i].innerText;
            array[i][0][1] = true; //inform about array for listView (true) or recipe.html (false)
            array[i][1] = recipeData.getElementsByTagName('title')[i].innerText;
            array[i][2] = recipeData.getElementsByTagName('description')[i].innerText;
            array[i][3] = recipeData.getElementsByTagName('picture')[i].innerText;
            array[i][4] = recipeData.getElementsByTagName('category')[i].innerText;
            var ingridientsOfRec = recipeData.getElementsByTagName('ingridients')[i];
            WebServiceHandler.getSkladniki(array, i, ingridientsOfRec);
        }
    })
}/*,
    getPrzepisyTest: function (array) { //cat. (and ing.) format 1!3!2
        WinJS.xhr({
            type: "post",
            url: "http://54.186.231.191:8080/SOAPService/RecipeService?wsdl",
            data: '<?xml version="1.0" encoding="utf-8"?>' +
                '   <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://WebService/"><soapenv:Header/><soapenv:Body><web:GetRepiceList>' +
        '         <categories>1!2!3!4!5!6</categories>' +
        '         <ingridients></ingridients>' +
        '         <user>abis</user>' +
        '         <pass>siba</pass>' +
        '      </web:GetRepiceList>' +
        '   </soapenv:Body>' +
        '   </soapenv:Envelope>',
            headers: {
                "Content-Type": "text/xml; charset=utf-8",
                "SOAPAction": "GetRepiceList"
            }
            // headers: { "Content-type": "application/x-www-form-urlencoded" },
        }).then(function (msg) {
            var xmlDoc = new Windows.Data.Xml.Dom.XmlDocument();
            xmlDoc.loadXml(msg.responseText); //load xml which include other xml 
            var realXmlDoc = new Windows.Data.Xml.Dom.XmlDocument();
            
            realXmlDoc.loadXml(xmlDoc.documentElement.childNodes[0].innerText.replace(/&/g, ''));
           // xmlDoc.loadXml(xmlDoc.documentElement.childNodes[0].innerText); //load right xml
            for (var i = 0; i < xmlDoc.documentElement.childNodes.length; i++) {
                var node = xmlDoc.documentElement.childNodes[i];
                console.log("node=" + node);
            }
        });
    }
    */
}