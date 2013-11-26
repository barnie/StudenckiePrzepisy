function createDB() {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    SQLite3JS.openAsync(dbPath).then(function (db) {
        db.runAsync('CREATE TABLE Kategorie (NAZWA TEXT PRIMARY KEY)');
        db.runAsync('CREATE TABLE Przepisy (opis TEXT PRIMARY KEY,zdjecie TEXT,kategorie int, FOREIGN KEY(kategorie) REFERENCES Kategorie(id))')
        db.runAsync('CREATE TABLE Miary (nazwa TEXT PRIMARY KEY,waga REAL)')
        db.runAsync('CREATE TABLE Skladniki (nazwa TEXT PRIMARY KEY)')
        db.runAsync('CREATE TABLE Sklad (nazwa_przepisu TEXT PRIMARY KEY)').then(function () {
            db.close()
        })
    });
}