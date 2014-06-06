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
        if (typeof (categories) === 'undefined')
        {
            if (this.defaultCategories == "") throw "Error: defaultCategories is empty";
            categories = this.defaultCategories;
        }
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
    
    getPrzepisy: function (array) { //cat. (and ing.) format 1!3!2
        return WinJS.xhr({
            type: "post",
            url: this.wsdlUrl,
            data: this.request(),
            headers: {
                "Content-Type": "text/xml; charset=utf-8",
                "SOAPAction": this.method
            }
        }).then(function (msg) {
            var xmlDoc = new Windows.Data.Xml.Dom.XmlDocument();
            xmlDoc.loadXml(msg.responseText); //load xml which include other xml 
            var rightSource = xmlDoc.documentElement.childNodes[0].innerText.replace(/&/g, ''); //remove & for fix problem with loadXml function
            xmlDoc.loadXml(rightSource); //load right xml
            for (var i = 0; i < xmlDoc.documentElement.childNodes.length; i++) {
                array[i] = new Array();
                array[i][0] = xmlDoc.documentElement.getElementsByTagName('id')[i].innerText;
                array[i][1] = xmlDoc.documentElement.getElementsByTagName('title')[i].innerText;
                array[i][2] = xmlDoc.documentElement.getElementsByTagName('description')[i].innerText;
                array[i][2] = xmlDoc.documentElement.getElementsByTagName('picture')[i].innerText;
            }
        });
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