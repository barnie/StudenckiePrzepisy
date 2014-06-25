PRAGMA foreign_keys = ON;
PRAGMA encoding="UTF-8";

create table typ_przepisu ( id INTEGER PRIMARY KEY, typ_dania TEXT, typ_kuchni TEXT);
create table przepisy ( id INTEGER PRIMARY KEY, id_typ_przepisu REFERENCES typ_przepisu(id), nazwa_przepisu TEXT, opis_przyrzadzania TEXT, ocena smallint );
create table sklad (id INTEGER PRIMARY KEY, id_przepisu REFERENCES przepisy(id));
create table wagi (id INTEGER PRIMARY KEY, miara FLOAT);
create table skladniki (id INTEGER PRIMARY KEY, id_miara REFERENCES wagi(id), nazwa_skladnika TEXT);

insert into typ_przepisu values(1, 'sniadanie', 'polska');
insert into przepisy values(1, 1, 'jajecznica', 'tluczesz jajka i na patelnie je ziaaa', 5);
insert into sklad values(1, 1);
insert into wagi values(1,100.0);
insert into skladniki values(1, 1, 'jajka');


