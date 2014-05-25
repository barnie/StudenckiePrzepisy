var kat = new Array();
//kategorie:
kat[0] = "INSERT OR IGNORE INTO Kategorie VALUES (1,'Dania główne','kat1.jpg')";
kat[1] = "INSERT OR IGNORE INTO Kategorie VALUES (2,'Zupy','kat2.jpg')";
kat[2] = "INSERT OR IGNORE INTO Kategorie VALUES (3,'Sałatki i surówki','kat3.jpg')";
kat[3] = "INSERT OR IGNORE INTO Kategorie VALUES (4,'Desery','kat4.jpg')";
kat[4] = "INSERT OR IGNORE INTO Kategorie VALUES (5,'Studenckie','kat5.jpg')";
kat[5] = "INSERT OR IGNORE INTO Kategorie VALUES (6,'Inne','kat6.jpg')";

//składniki:
kat[6] = "INSERT OR IGNORE INTO skladnik(id, nazwa) VALUES (1,'polędwica wieprzowa')";
kat[7] = "INSERT OR IGNORE INTO skladnik(id, nazwa) VALUES (2,'jajko')";
kat[8] = "INSERT OR IGNORE INTO skladnik(id, nazwa) VALUES (3,'sól')";
kat[9] = "INSERT OR IGNORE INTO skladnik(id, nazwa) VALUES (4,'pieprz czarny')";
kat[10] = "INSERT OR IGNORE INTO skladnik(id, nazwa) VALUES (5,'czosnek')";
kat[11] = "INSERT OR IGNORE INTO skladnik(id, nazwa) VALUES (6,'mąka')";
kat[12] = "INSERT OR IGNORE INTO skladnik(id, nazwa) VALUES (7,'bułka tarta')";
kat[13] = "INSERT OR IGNORE INTO skladnik(id, nazwa) VALUES (8,'olej')";
kat[14] = "INSERT OR IGNORE INTO skladnik(id, nazwa) VALUES (9,'koperek'), (10,'oliwa'), (11,'miód'), (12,'cytryna'), (13,'kolendra'), (14,'łosoś'), (15,'majonez'), (16,'ogórek'), (17,'rzodkiewka'), (18,'kalafior'), (19,'brokuł'), (20,'cukier'), (21,'cebula'), (22,'masło'), (23,'mleko'), (24,'gałka muszkatołowa'), (25,'ser'), (26,'szynka'), (27,'śmietana'), (28,'truskawki'), (29,'esencja migdałowa'), (30,'drozdze'), (31,'woda'), (32,'płatki owsiane'), (33,'slonecznik'), (34,'kminek'),(35,'grzyby'), (36,'bulion drobiowo-warzywny'), (37,'chleb'), (38,'jogurt naturalny'), ('39','żelfix'), ('40','otręby granulowane'), ('41','serek homogenizowany'),('42','czerwona papryka'), ('43','pomidor'), ('44','spirytus'), ('45','woda'), ('46','cukierki ice')";

//przepisy:
kat[15] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (1, 1, 'Kotlety z polędwicy wieprzowej', 'Polędwiczki myjemy, obsuszamy. Dzielimy  na  porcje. Mięso delikatnie rozbijamy. Solimy równomiernie obie strony. Jedną stronę oprószamy pieprzem, drugą nacieramy przeciśniętym przez praskę czosnkiem. Tak przyprawione mięso odstawiamy na ok. 10 minut. Oprószamy  z obu stron mąką pszenną, obtaczamy w rozmąconym w wodzie jajku i tartej bułce. Smażymy z obu stron na rozgrzanym na patelni oleju roślinnym ok. 9 minut. Podajemy  do  drugiego  dania  obiadowego  z  ziemniakami  i surówką  z  kapusty pekińskiej z ananasem i chrzanem lub z marchewką w beszamelu, bądź też z inną surówką według upodobań.' ,'1.jpg')";
kat[16] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (1,1,'dag', 25)";
kat[17] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (1,2,'', 1)";
kat[18] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (1,3,'', '')";
kat[19] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (1,4,'', '')";
kat[20] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (1,5,'ząb', 1)";
kat[21] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (1,6,'', '')";
kat[22] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (1,7,'', '')";
kat[23] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (2, 1, 'Pieczony łosoś', 'W miseczce wymieszać koperek z oliwą, miodem, sokiem z cytryny, kolendrą, czosnkiem, solą i pieprzem. Łososia opłukać i osuszyć papierowym ręcznikiem. Ułożyć na folii spożywczej skórą do dołu, pokryć równomiernie marynatą, owinąć folią i wstawić do lodówki na 30 minut. Rozgrzać piekarnik do 220 stopni C. Z łososia zdjąć folię i ułożyć w brytfannie skórą do dołu. Piec przez 13-15 minut.' ,'2.jpg')";
kat[24] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (2,10,'łyżki', 3)";
kat[25] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (2,9,'g', 30)";
kat[26] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (2,11,'łyżka', 1)";
kat[27] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (2,12,'', 1)";
kat[28] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (2,5,'ząbki', 2)";
kat[29] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (2,13,'łyżeczka', 1)";
kat[30] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (2,14,'kg', 1)";
kat[31] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (2,3,'', '')";
kat[32] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (2,4,'', '')";
kat[33] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (3, 5, 'Jajka w majonezie', 'Ogórek, rzodkiewki i koperek drobno pokrój. Jajka przekrój na pół, wyjmij żółtka, połącz z Majonezem i warzywami. Dopraw do smaku solą i pieprzem. Z masy formuj kulki i nakładaj je do białek. Przed podaniem jajka posyp koperkiem.' ,'3.jpg')";
kat[34] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (3,15,'łyżka', 1)";
kat[35] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (3,2,'szt', 4)";
kat[36] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (3,16,'szt', 0.5)";
kat[37] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (3,17,'szt', 6)";
kat[38] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (3,3,'', '')";
kat[39] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (3,4,'', '')";
kat[40] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (3,9,'szczypta', 3)";
kat[41] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (4, 1, 'Kalafior i brokuły pod beszamelem' , 'Kalafior i brokuł dzielimy na różyczki i blanszujemy w osolonym i doprawionym łyżeczką cukru wrzątku przez ok. 5 minut. Szynkę kroimy w drobną kostkę. Cebulę obieramy i tniemy w cienkie piórka. W żaroodpornym naczyniu posmarowanym łyżką masła układamy różyczki kalafiora i brokuła, posypujemy rozdrobnioną szynką i cebulą i ewentualnie posypujemy delikatnie solą i pieprzem. Pozostałe 2 łyżki masła roztapiamy w rondlu, dodajemy mąkę, podgrzewamy do jej zeszklenia się i stale mieszając wlewamy mleko (porcjami). Gotujemy, aż sos zgęstnieje. Doprawiamy solą, pieprzem i gałką muszkatołową. Sosem beszamelowym zalewamy przygotowane warzywa i zapiekamy 15 minut od chwili zawrzenia w temp. 180°C. Przy końcu posypujemy startym żółtym serem.' ,'4.jpg')";
kat[42] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,18,'kg', 1)";
kat[43] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,19,'kg', 1)";
kat[44] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,3,'łyżka', 1)";
kat[45] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,20,'łyżeczka', 1)";
kat[46] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,26,'dag', 20)";
kat[47] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,21,'dag', '10-15')";
kat[48] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,22,'łyżeczki', 3)";
kat[49] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,6,'łyżki', 2)";
kat[50] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,23,'szklanki', 2)";
kat[51] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,24,'łyżeczka', 1/2)";
kat[52] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,25,'dag', 20)";
kat[53] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (4,4,'', '')";
kat[54] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (5, 4, 'Placuszki z truskawkami' , 'Odszypułkowane i umyte truskawki kroimy w talarki o grubości 1 cm. Białka ubijamy mikserem na sztywną pianę, dodajemy cukier, a następnie po 1 żółtku. Odstawiamy mikser i wsiewamy małymi porcjami mąkę, stale mieszając łyżką. Dodajemy śmietanę, całość jeszcze raz mieszamy na jednolitą masę. Na rozgrzaną patelnię z olejem kładziemy łyżką małe porcje ciasta. Na każdą dajemy po kilka talarków truskawek i ponownie ciasto, tylko tyle, aby przykryć truskawki. Smażymy na złoty kolor. Natomiast stronę z truskawkami znacznie krócej, aby nie wysmażyć soku. Podajemy posypane cukrem pudrem i polane musem truskawkowym.' ,'5.jpg')";
kat[55] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (5,2,'', 3)";
kat[56] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (5,6,'', 14)";
kat[57] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (5,20,'płaskie łyżki', 2)";
kat[58] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (5,3,'łyżeczki', 1/2)";
kat[59] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (5,27,'łyżki', 2)";
kat[60] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (5,28,'dag', 20)";
kat[61] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (5,29,'krople', '5-6')";
kat[62] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (5,8,'', '')";
kat[63] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (6, 6, 'Chleb łatwy i chrupiący' , 'Drożdże rozcieramy w dużej misce z cukrem i kilkoma łyżkami wody. Następnie dodajemy pozostałe składniki i wyrabiamy ciasto przez kilka minut. Powinno być gładkie. Ciasto lekko oprószamy mąką, przykrywamy ściereczką i odstawiamy do wyrośnięcia na ok. godzinę. Dwie podłużne foremki (długość ok. 30 cm) smarujemy tłuszczem i posypujemy mąką. Wyrośnięte ciasto mieszamy ponownie, przekładamy do foremek i odstawiamy do wyrośnięcia. Gdy ciasto zrówna się z foremką wkładamy do nagrzanego piekarnika do 175°C, na ok. 40–45 minut. Gdy chleb jest już upieczony smarujemy go po wierzchu wodą, jedną łyżką na bochenek. Chleb zostawiamy jeszcze na ok. 5 minut w piekarniku, następnie wyjmujemy go. Studzimy na kratce i przechowujemy w lnianej ściereczce. Smacznego!' ,'6.jpg')";
kat[64] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (6,30,'g', 10)";
kat[65] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (6,20,'łyżka', 1)";
kat[66] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (6,6,'kg', 1)";
kat[67] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (6,31,'szklanki', 3)";
kat[68] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (6,32,'szklanka', 1)";
kat[69] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (6,8,'łyżek', 8)";
kat[70] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (6,3,'', '')";
kat[71] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (6,33,'szklanki', '3/4')";
kat[72] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (6,34,'łyżeczki', 2)";
kat[73] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (7, 3, 'Grzyby w smietanie' , 'Grzyby wyczyścić pokroić na mniejsze kawałki i podgotować w osolonej wodzie jakieś 15mnut. W międzyczasie cebulę oczyścić i pokroić w piórka - usmażyć na oliwie z dodatkiem masła. Następnie odcedzić grzyby i wrzucić do cebulki, przesmażyć jeszcze chwilę. Wbijamy kolejno po jednym jajku doprawiamy - gdy jajka powoli zaczną się  ścinać dodać śmietanę i szybko wymieszać. Podawać na gorąco.' ,'7.jpg')";
kat[74] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (7,35,'g', '300')";
kat[75] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (7,2,'', '2')";
kat[76] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (7,27,'łyżki', '2')";
kat[77] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (7,21,'', '1')";
kat[78] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (7,22,'łyżka', 1)";
kat[79] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (7,8,'łyżki', '2')";
kat[80] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (7,3,'', '')";
kat[81] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (7,4,'', '')";
kat[82] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (8, 2, 'Zupa cebulowa' , 'Cebulę obieramy i kroimy w kostkę, olej dobrze rozgrzewamy na patelni i wrzucamy cebulę szklimy lecz pilnujemy by nie zrobiła się brązowa. Wyciskamy do niej czosnek, przesmażamy mieszając i zdejmujemy z ognia. W garnku podgrzewamy 2 litry bulionu ugotowanego na niewielkiej ilości mięsa (tak aby był dość chudy), dodajemy cebulę, gotujemy aż cebula będzie miękka. Zupę przyprawiamy do smaku, miksujemy. Na patelni rozgrzewamy olej, smażymy chleb na rumiano, kroimy w kosteczkę, takimi grzankami oraz żółtym serem posypujemy zupę, tuż przed podaniem.' ,'8.jpg')";
kat[83] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (8,21,'kg', '1')";
kat[84] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (8,36,'l', '2')";
kat[85] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (8,5,'ząbki', '2')";
kat[86] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (8,3,'', '')";
kat[87] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (8,4,'', '')";
kat[88] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (8,37,'kromki', '4')";
kat[89] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (8,4,'tarty', '')";
kat[90] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (9, 6, 'Sałatka czosnkowa' , 'Do swojej sałatki nie wlewam majonezu z oszczędności kalorii oraz zachowania jego zdrowego wydania ;) Jogurt przelewamy do miseczki. Wyciskamy czosnek prosto do jogurtu. Dodajemy płatki owsiane. Doprawiamy solą i pieprzem. Całość dobrze mieszamy i odstawiamy do lodówki na około 2 godziny aby się ‚przegryzło’.' ,'9.jpg')";
kat[91] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (9,38,'mały', '1')";
kat[92] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (9,5,'ząbków', '2-4')";
kat[93] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (9,3,'', '')";
kat[94] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (9,4,'', '')";
kat[95] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (10, 6, 'Dżem truskawkowy' , 'Truskawki umyć, odszczypułkować. Większe sztuki pokroić ( ja wkładałam cały nóż pionowo do garnka i kroiłam je masowo ). Zasypać cukrem  i odstawić aż puszczą sok – około godziny jeśli jest gorąco 3 jeśli temperatura jest umiarkowana. Sok można wlać ( zostawiając 1/3 całości ) i zapasteryzować w słoikach lub butelkach. (około 20-30 minut od zagotowania). Po czym gotować na wolnym ogniu do odparowania syropu jakieś 2 godziny. Po tym czasie, część truskawek zmiksować ( jeśli się nie rozpadły podczas gotowania ). Wlać sok z cytryny, wsypać pektynę, wymieszać i gotować jeszcze 5 minut. Przelewać do suchych, czystych słoików, najlepiej podgrzanych, zakręcać i odstawiać do góry dnem, do wystudzenia. Tak przygotowany dżem może stać dość długo. Co więcej pektyna zapobiega psuciu się przetworów. Miejcie na uwadze też to, że truskawki mają dużo soku  więc ich ilość znacznie się zmniejszy podczas gotowania. Smacznego!’.' ,'10.jpg')";
kat[96] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (10,39,'op.', '2')";
kat[97] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (10,20,'g', '700')";
kat[98] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (10,12,'szt', '1')";
kat[99] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (10,28,'kg', '4')";
kat[100] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (11, 4, 'Szybki deser truskawkowy' , 'Truskawki kroimy w ćwiartki serek homogenizowany mieszamy z paroma łyżkami otrębów układamy warstwowo otręby, serek, otręby, truskawki, serek (lub jak kto woli inną kolejność) i zajadamy ;) smakuje genialnie! ;)' ,'11.jpg')";
kat[101] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (11,28,'szt', '4')";
kat[102] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (11,40,'', '')";
kat[103] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (11,41,'', '')";
kat[104] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (12, 4, 'Koktajl truskawkowy' , 'Przepis jest stosunkowo prosty. Wkładamy wszystko do jakiegoś naczyńka i miksujemy :) Najlepszy schłodzony. Smacznego!' ,'12.jpg')";
kat[105] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (12,28,'kg', '1')";
kat[106] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (12,38,'l', '0.5-1')";
kat[107] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (12,20,'łyżki', '1-2')";
kat[108] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (13, 3, 'Sałatka z papryki' , 'Paprykę i cebulę pokroić w drobną kostkę. Pomidory sparzyć, obrać, usunąć gniazda nasienne. Pokroić w kostkę. Wszystkie składniki przełożyć do miski. Dodać majonez oraz śmietanę. Doprawić solą i pieprzem, dokładnie wymieszać.!' ,'13.jpg')";
kat[109] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (13,42,'szt', '1')";
kat[110] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (13,43,'szt', '2')";
kat[111] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (13,21,'szt', '1')";
kat[112] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (13,15,'łyżki', '2')";
kat[113] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (13,27,'łyżki', '2')";
kat[114] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (13,3,'', '')";
kat[115] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (13,4,'', '')";
kat[116] = "INSERT OR IGNORE INTO przepis(id, id_kategorii, nazwa, opis, zdjecie) VALUES (14, 5, 'Iceówka' , 'Cukierki zalewamy wrzącą wodą i mieszamy aż do całkowitego rozpuszczenia, można dodatkowo lekko podgrzewać żeby przyspieszyć cały proces choć i bez tego po kilku minutach wszystko się ładnie rozpuszcza. Czekamy aż uzyskany syrop całkowicie ostygnie i wlewamy do niego spirytus. Przelewamy iceówke do butelek i odkładamy do lodówki na przynajmniej kilka dni żeby się przegryzła. Można dowolnie zmieniać proporcje składników w zależności od pożądanego kopnięcia oraz stopnia słodkości. Wchodzi świetnie, bez przepitki.' ,'14.jpg')";
kat[117] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (14,44,'l', '0.4')";
kat[118] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (14,31,'l', '0.5')";
kat[119] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (14,46,'g', '300')";
kat[120] = "INSERT OR IGNORE INTO przepis_skladnik VALUES (9,32,'według uznania', '')";






function default_insert() {
    var dbPath = Windows.Storage.ApplicationData.current.localFolder.path + '\\przepisy_db.sqlite';
    SQLite3JS.openAsync(dbPath).then(function (db) {
        var i = 0;
        for (i = 0; i < kat.length - 1; i++) {
            db.runAsync(kat[i]).done(function () {

            }), function (error) {
                if (db) {
                    db.close()
                }
                console.log("BLAD przy dodawaniu " + error.message);
            }
        }
        return db.runAsync(kat[i]).
        done(function () {
            console.log('Baza Zapchana:)');
            db.close();
            var array = [];
            getKategorie(array).then(function () {
                WinJS.Navigation.navigate("/pages/categories/categories.html", array);
            })
        }, function (error) {
            if (db) {
                db.close();
            }
            console.log('ERROR DODANIE KATEGORII' + error.message);
        })
    });
}
