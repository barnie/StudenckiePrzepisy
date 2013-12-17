
/*
    Tworzy baze, podstawowy pusty schemat.
*/

function createDB() {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    return SQLite3JS.openAsync(dbPath).then(function (db) {
        db.runAsync('create table IF NOT EXISTS Kategorie (id integer PRIMARY KEY AUTOINCREMENT, rodzaj TEXT)');
        db.runAsync('create table IF NOT EXISTS Przepis (id integer PRIMARY KEY AUTOINCREMENT, id_kategorii integer REFERENCES Kategorie(id), nazwa TEXT NOT NULL UNIQUE, opis TEXT, zdjecie TEXT)')

        db.runAsync('create table IF NOT EXISTS Skladnik (id integer PRIMARY KEY AUTOINCREMENT,nazwa TEXT, ile INT DEFAULT 1)')
        db.runAsync('create table IF NOT EXISTS Przepis_Skladnik (id_przepis integer REFERENCES Przepis(id), id_skladnik integer REFERENCES Skladnik(id),miara TEXT,ile REAL, PRIMARY KEY(id_przepis,id_skladnik))').then(function () {
            db.close()
        })
    });
}

/*
    Dodaje kategorie.
*/

function addKategorie(nazwa) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    return SQLite3JS.openAsync(dbPath).then(function (db) {
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

/*
    Dodaje wiele kategorii przyjmuje tablice kategorii.
*/

function addManyKategorie(array) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    return SQLite3JS.openAsync(dbPath).then(function (db) {
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

/*
    Dodaje skladnik.

*/

function addSkladnik(nazwa) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    return SQLite3JS.openAsync(dbPath).then(function (db) {
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

/*
    Dodaje wiele skladnikow, baza jest asynchroniczna w ten sposob oszczedzamy czas.
*/

function addManySkladnik(array) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    return SQLite3JS.openAsync(dbPath).then(function (db) {
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

/*
    Dodaje przepis (id_kategorii, nazwa,opis, url_Zdjecie, tablica skladnikow)
    Przyklad uzycia : 
        var tab = [];
        tab[0] = new Array(); tab[1] = new Array();
        tab[0][0] = 1; tab[0][1] = 'szklanka'; tab[0][2] = 2;
        tab[1][0] = 2; tab[1][1] = 'lyzka'; tab[1][2] = 3;
        addPrzepis(1,'aaa','xxx','img',tab)
*/

function addPrzepis(id_kategorii, nazwa, opis, zdjecie, tab) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    return SQLite3JS.openAsync(dbPath).then(function (db) {
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

/*
    Zwraca jednowymiarowa tablice Kategorii
    Zwraca pole rodzaj i zapisuje je do podanej tablicy
*/

function getKategorie(array) {
    
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    var i = 0;
    return SQLite3JS.openAsync(dbPath)
              .then(function (db) {
                  console.log('DB opened');
                  return db.eachAsync('SELECT * FROM kategorie;', function (row) {
                      array[i++] = row.rodzaj;
                      console.log('##Kategorie_Select : ' + row.rodzaj);
                  });
              }, function (error) {
                  if (db) {
                      db.close();
                  }
                  console.log('ERROR Select * from kategorie ' + error.message);
              })
             .then(function (db) {
                 console.log('close the db');
                 db.close();
             }).then(function () {
                 return array;
             });
    
}

/*
    zwraca dwuwymiarowa tablice Przepis (indeksy :  0 - id_kategorii, 1 - nazwa
        2 - opis, 3 - adres_do_zdjecia
    example uzycia : 
        var array = [];
        getPrzepis(array).then(function () {
            for (var i = 0; i < array.length; i++) {
                    console.log(array[i][1]);
            }
        })
*/

function getPrzepis(array) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    var i = 0;
    return SQLite3JS.openAsync(dbPath)
              .then(function (db) {
                  console.log('DB opened');
                  return db.eachAsync('SELECT * FROM Przepis;', function (row) {
                      array[i] = new Array();
                      array[i][0] = row.id_kategorii;
                      array[i][1] = row.nazwa;
                      array[i][2] = row.opis;
                      array[i][3] = row.zdjecie;
                      i++;
                  });
              }, function (error) {
                  if (db) {
                      db.close();
                  }
                  console.log('ERROR Select * from kategorie ' + error.message);
              })
             .then(function (db) {
                 console.log('close the db');
                 db.close();
             }).then(function () {
                 return array;
             });
}

/*
    Zwraca dwuwymiarowa tabele Przepis_Skladnik
    Indeksy kazdego wiersza : 
    0 - id_przepis
    1 - id_skladnik
    2 - miara
    3 - ile
*/

function getPrzepis_Skladnik(array) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    var i = 0;
    return SQLite3JS.openAsync(dbPath)
              .then(function (db) {
                  console.log('DB opened');
                  return db.eachAsync('SELECT * FROM Przepis_Skladnik;', function (row) {
                      array[i] = new Array();
                      array[i][0] = row.id_przepis;
                      array[i][1] = row.id_skladnik;
                      array[i][2] = row.miara;
                      array[i][3] = row.ile;
                      i++;
                  });
              }, function (error) {
                  if (db) {
                      db.close();
                  }
                  console.log('ERROR Select * Przepis_Skladnik ' + error.message);
              })
             .then(function (db) {
                 console.log('close the db');
                 db.close();
             }).then(function () {
                 return array;
             });
}

/*
    Zwraca Skladniki w jednowymiarowej tablicy :
    zwraca pole nazwe dla kazdego skladnika
*/

function getSkladnik(array) {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    var i = 0;
    return SQLite3JS.openAsync(dbPath)
              .then(function (db) {
                  console.log('DB opened');
                  return db.eachAsync('SELECT * FROM Skladnik;', function (row) {
                      array[i++] = row.nazwa;
                  });
              }, function (error) {
                  if (db) {
                      db.close();
                  }
                  console.log('ERROR Select * Skladnik ' + error.message);
              })
             .then(function (db) {
                 console.log('close the db');
                 db.close();
             }).then(function () {
                 return array;
             });
}

