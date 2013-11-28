function createDB() {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    SQLite3JS.openAsync(dbPath).then(function (db) {
        db.runAsync('CREATE TABLE IF NOT EXISTS Kategorie (NAZWA TEXT PRIMARY KEY)');
        db.runAsync('CREATE TABLE IF NOT EXISTS Przepisy (opis TEXT PRIMARY KEY,zdjecie TEXT,kategorie int, FOREIGN KEY(kategorie) REFERENCES Kategorie(id))')
        db.runAsync('CREATE TABLE IF NOT EXISTS Miary (nazwa TEXT PRIMARY KEY,waga REAL)')
        db.runAsync('CREATE TABLE IF NOT EXISTS Skladniki (nazwa TEXT PRIMARY KEY)')
        db.runAsync('CREATE TABLE IF NOT EXISTS Dozwolone_miary(nazwa_miary TEXT, nazwa_skladnika TEXT, FOREIGN KEY(nazwa_miary) REFERENCES Miary(nazwa), FOREIGN KEY(nazwa_skladnika) REFERENCES Skladniki(nazwa), PRIMARY KEY(nazwa_miary,nazwa_skladnika))')
        db.runAsync('CREATE TABLE IF NOT EXISTS Sklad (nazwa_przepisu TEXT PRIMARY KEY)').then(function () {
            init_miary()
            db.close()
        })
    });
}

function init_miary() {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    SQLite3JS.openAsync(dbPath).then(function (db) {
        db.runAsync('INSERT INTO Miary VALUES("lyzka",100.0)')
        db.runAsync('INSERT INTO Miary VALUES("lyzeczka",50.0)')
        db.runAsync('INSERT INTO Miary VALUES("szklanka",250.0)')
        db.runAsync('INSERT INTO Miary VALUES("gramy",1.0)')
        db.runAsync('INSERT INTO Miary VALUES("dekagramy",10.0)')
        db.runAsync('INSERT INTO Miary VALUES("kilo",1000.0)')
        db.runAsync('INSERT INTO Miary VALUES("sloik",30.0)')
        db.runAsync('INSERT INTO Miary VALUES("kieliszek",50.0)').then(function () {
            db.close()
        })
    });
}

function sample_insert() {

}
