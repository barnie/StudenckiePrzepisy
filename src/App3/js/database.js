function createDB() {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    SQLite3JS.openAsync(dbPath).then(function (db) {
        db.runAsync('create table IF NOT EXISTS Kategorie (id integer PRIMARY KEY AUTOINCREMENT, rodzaj TEXT)');
        db.runAsync('create table IF NOT EXISTS Przepis (id integer PRIMARY KEY AUTOINCREMENT, id_kategorii integer REFERENCES Kategorie(id), nazwa TEXT, opis TEXT, zdjecie TEXT)')

        db.runAsync('create table IF NOT EXISTS Skladnik (id integer PRIMARY KEY AUTOINCREMENT,nazwa TEXT)')
        db.runAsync('create table IF NOT EXISTS Przepis_Skladnik (id_przepis integer REFERENCES Przepis(id), id_skladnik integer REFERENCES Skladnik(id),miara TEXT,ile REAL, PRIMARY KEY(id_przepis,id_skladnik))').then(function () {
            db.close()
        })
    });
}


function addKategorie(nazwa) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    SQLite3JS.openAsync(dbPath).then(function (db) {
        return db.runAsync("INSERT INTO Kategorie (rodzaj) VALUES (:rodzaj)", { rodzaj: nazwa }).
        done(function () {
            console.log('Dodano nowa kategorie : ' + nazwa);
            db.close();
        }, function (error) {
            if (db) {
                db.close();
            }
            console.log('ERROR DODANIE KATEGORII' + error.message);

        })
    });
}

function addManyKategorie(array) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    SQLite3JS.openAsync(dbPath).then(function (db) {
        var i = 0;
        for (i = 0; i < array.length - 1; i++) {
            db.runAsync("INSERT INTO Kategorie (rodzaj) VALUES (:rodzaj)", { rodzaj: array[i] })
        }
        return db.runAsync("INSERT INTO Kategorie (rodzaj) VALUES (:rodzaj)", { rodzaj: array[i] }).
        done(function () {
            console.log('Dodano duzo kategorii : ');
            db.close();
        }, function (error) {
            if (db) {
                db.close();
            }
            console.log('ERROR DODANIE KATEGORII' + error.message);
        })
    });
}

function addSkladnik(nazwa) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    SQLite3JS.openAsync(dbPath).then(function (db) {
        return db.runAsync("INSERT INTO Skladnik (nazwa) VALUES (:nazwa)", { nazwa: nazwa }).
        done(function () {
            console.log('Dodano nowy skladnik : ' + nazwa);
            db.close();
        }, function (error) {
            if (db) {
                db.close();
            }
            console.log('ERROR PRZY DODAWANIU SKLADNIKA' + error.message);
        })
    });
}

function addManySkladnik(array) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    SQLite3JS.openAsync(dbPath).then(function (db) {
        var i = 0;
        for (i = 0; i < array.length - 1; i++) {
            db.runAsync("INSERT INTO Skladnik (nazwa) VALUES (:nazwa)", { nazwa: array[i] })
        }
        return db.runAsync("INSERT INTO Skladnik (nazwa) VALUES (:nazwa)", { nazwa: array[i] }).
        done(function () {
            console.log('Dodano duzo skladnik : ');
            db.close();
        }, function (error) {
            if (db) {
                db.close();
            }
            console.log('ERROR DODAWANIE DUZO SKLADNIKOW' + error.message);
        })
    });
}


function addPrzepis(id_kategorii, nazwa, opis, zdjecie, tab) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    SQLite3JS.openAsync(dbPath).then(function (db) {
        return db.runAsync("INSERT INTO Przepis (id_kategorii,nazwa,opis,zdjecie) VALUES (:idkategorii, :nazwa, :opis, :zdjecie)", { idkategorii: id_kategorii, nazwa: nazwa, opis: opis, zdjecie: zdjecie }).
        then(function () {
            db.eachAsync("SELECT * from Przepis Where nazwa = ? ", [nazwa], function (row) {
                console.log(row.id);
                for (var i = 0; i < tab.length; i++) {
                    db.runAsync("INSERT INTO Przepis_Skladnik (id_przepis,id_skladnik,miara,ile) VALUES(:idprzepis, :idskladnik, :miara, :ile)", { idprzepis: row.id, idskladnik: tab[i][0], miara: tab[i][1], ile: tab[i][2] })
                }
            })
        }, function (error) {
            if (db) {
                db.close();
            }
            console.log('ERROR DODAWANIE DUZO SKLADNIKOW' + error.message);
        })
    });
}

function getKategorie(array) {
    
    return new WinJS.Promise(function () {
        var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';

        var i = 0;
        SQLite3JS.openAsync(dbPath)
              .then(function (db) {
                  console.log('DB opened');
                  return db.eachAsync('SELECT * FROM kategorie;', function (row) {
                      array[i++] = row.rodzaj;
                      console.log('Get a ' + row.rodzaj);
                  });
              })
             .then(function (db) {
                 console.log('close the db');
                 db.close();
             }).then(function () {
                 return array;
             });
        return array;
    })
}


