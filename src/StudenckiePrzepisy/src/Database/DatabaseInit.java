package Database;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by piotr on 09.05.14.
 */
public class DatabaseInit {

    private Context context;

    private DatabaseInit() {
    }

    public DatabaseInit(Context context) {
        this.context = context;
    }

    private boolean firstRunOfApplication() {
        return false;
    }

    public void initDB() {
        initKategorie();
        initSkladnik();
        initPrzepis();
        initPrzepisSkladnik();
    }


    public void initSkladnik() {
        ArrayList<Skladnik> skladniki = new ArrayList<Skladnik>();
        String tab[] = {"poledwica wieprzowa", "jajko", "sól", "pieprz czarny", "czosnek", "mąka", "bułka tarta", "olej", "koperek", "oliwa",
                "miód", "cytryna", "kolendra", "łosoś", "majonez", "ogórek", "rzodkiewka", "kalafior", "brokuł", "cukier",
                "cebula", "masło", "mleko", "gałka muszkatołowa", "ser", "szynka", "śmietana", "truskawki", "esencja migdałowa",
                "drozdze", "woda", "płatki owsiane", "slonecznik", "kminek", "grzyby", "bulion drobiowo-warzywny", "chleb",
                "jogurt naturalny", "żelfix", "otręby granulowane", "serek homogenizowany", "czerwona papryka", "pomidor", "spirytus", "woda", "cukierki ice"};
        for (String s : tab)
            skladniki.add(new Skladnik(s, 0));
        Database db = new Database(context, null, null, 1);
        db.addManySkladnik(skladniki);
    }

    public void initPrzepis() {
        Przepis przepisy[] = {
                new Przepis(1, 1, "Kotlety z polędwicy wieprzowej", "Polędwiczki myjemy, obsuszamy. Dzielimy  na  porcje. Mięso delikatnie rozbijamy. Solimy równomiernie obie strony. Jedną stronę oprószamy pieprzem, drugą nacieramy przeciśniętym przez praskę czosnkiem. Tak przyprawione mięso odstawiamy na ok. 10 minut. Oprószamy  z obu stron mąką pszenną, obtaczamy w rozmąconym w wodzie jajku i tartej bułce. Smażymy z obu stron na rozgrzanym na patelni oleju roślinnym ok. 9 minut. Podajemy  do  drugiego  dania  obiadowego  z  ziemniakami  i surówką  z  kapusty pekińskiej z ananasem i chrzanem lub z marchewką w beszamelu, bądź też z inną surówką według upodobań.", "f1.jpg"),
                new Przepis(2, 1, "Pieczony łosoś", "W miseczce wymieszać koperek z oliwą, miodem, sokiem z cytryny, kolendrą, czosnkiem, solą i pieprzem. Łososia opłukać i osuszyć papierowym ręcznikiem. Ułożyć na folii spożywczej skórą do dołu, pokryć równomiernie marynatą, owinąć folią i wstawić do lodówki na 30 minut. Rozgrzać piekarnik do 220 stopni C. Z łososia zdjąć folię i ułożyć w brytfannie skórą do dołu. Piec przez 13-15 minut.", "f2.jpg"),
                new Przepis(3, 5, "Jajka w majonezie", "Ogórek, rzodkiewki i koperek drobno pokrój. Jajka przekrój na pół, wyjmij żółtka, połącz z Majonezem i warzywami. Dopraw do smaku solą i pieprzem. Z masy formuj kulki i nakładaj je do białek. Przed podaniem jajka posyp koperkiem.", "f3.jpg"),
                new Przepis(4, 1, "Kalafior i brokuły pod beszamelem", "Kalafior i brokuł dzielimy na różyczki i blanszujemy w osolonym i doprawionym łyżeczką cukru wrzątku przez ok. 5 minut. Szynkę kroimy w drobną kostkę. Cebulę obieramy i tniemy w cienkie piórka. W żaroodpornym naczyniu posmarowanym łyżką masła układamy różyczki kalafiora i brokuła, posypujemy rozdrobnioną szynką i cebulą i ewentualnie posypujemy delikatnie solą i pieprzem. Pozostałe 2 łyżki masła roztapiamy w rondlu, dodajemy mąkę, podgrzewamy do jej zeszklenia się i stale mieszając wlewamy mleko (porcjami). Gotujemy, aż sos zgęstnieje. Doprawiamy solą, pieprzem i gałką muszkatołową. Sosem beszamelowym zalewamy przygotowane warzywa i zapiekamy 15 minut od chwili zawrzenia w temp. 180°C. Przy końcu posypujemy startym żółtym serem.", "f4.jpg"),
                new Przepis(5, 4, "Placuszki z truskawkami", "Odszypułkowane i umyte truskawki kroimy w talarki o grubości 1 cm. Białka ubijamy mikserem na sztywną pianę, dodajemy cukier, a następnie po 1 żółtku. Odstawiamy mikser i wsiewamy małymi porcjami mąkę, stale mieszając łyżką. Dodajemy śmietanę, całość jeszcze raz mieszamy na jednolitą masę. Na rozgrzaną patelnię z olejem kładziemy łyżką małe porcje ciasta. Na każdą dajemy po kilka talarków truskawek i ponownie ciasto, tylko tyle, aby przykryć truskawki. Smażymy na złoty kolor. Natomiast stronę z truskawkami znacznie krócej, aby nie wysmażyć soku. Podajemy posypane cukrem pudrem i polane musem truskawkowym.", "f5.jpg"),
                new Przepis(6, 6, "Chleb łatwy i chrupiący", "Drożdże rozcieramy w dużej misce z cukrem i kilkoma łyżkami wody. Następnie dodajemy pozostałe składniki i wyrabiamy ciasto przez kilka minut. Powinno być gładkie. Ciasto lekko oprószamy mąką, przykrywamy ściereczką i odstawiamy do wyrośnięcia na ok. godzinę. Dwie podłużne foremki (długość ok. 30 cm) smarujemy tłuszczem i posypujemy mąką. Wyrośnięte ciasto mieszamy ponownie, przekładamy do foremek i odstawiamy do wyrośnięcia. Gdy ciasto zrówna się z foremką wkładamy do nagrzanego piekarnika do 175°C, na ok. 40–45 minut. Gdy chleb jest już upieczony smarujemy go po wierzchu wodą, jedną łyżką na bochenek. Chleb zostawiamy jeszcze na ok. 5 minut w piekarniku, następnie wyjmujemy go. Studzimy na kratce i przechowujemy w lnianej ściereczce. Smacznego!", "f6.jpg"),
                new Przepis(7, 3, "Grzyby w smietanie", "Grzyby wyczyścić pokroić na mniejsze kawałki i podgotować w osolonej wodzie jakieś 15mnut. W międzyczasie cebulę oczyścić i pokroić w piórka - usmażyć na oliwie z dodatkiem masła. Następnie odcedzić grzyby i wrzucić do cebulki, przesmażyć jeszcze chwilę. Wbijamy kolejno po jednym jajku doprawiamy - gdy jajka powoli zaczną się  ścinać dodać śmietanę i szybko wymieszać. Podawać na gorąco.", "f7.jpg"),
                new Przepis(8, 2, "Zupa cebulowa", "Cebulę obieramy i kroimy w kostkę, olej dobrze rozgrzewamy na patelni i wrzucamy cebulę szklimy lecz pilnujemy by nie zrobiła się brązowa. Wyciskamy do niej czosnek, przesmażamy mieszając i zdejmujemy z ognia. W garnku podgrzewamy 2 litry bulionu ugotowanego na niewielkiej ilości mięsa (tak aby był dość chudy), dodajemy cebulę, gotujemy aż cebula będzie miękka. Zupę przyprawiamy do smaku, miksujemy. Na patelni rozgrzewamy olej, smażymy chleb na rumiano, kroimy w kosteczkę, takimi grzankami oraz żółtym serem posypujemy zupę, tuż przed podaniem.", "f8.jpg"),
                new Przepis(9, 6, "Sałatka czosnkowa", "Do swojej sałatki nie wlewam majonezu z oszczędności kalorii oraz zachowania jego zdrowego wydania ;) Jogurt przelewamy do miseczki. Wyciskamy czosnek prosto do jogurtu. Dodajemy płatki owsiane. Doprawiamy solą i pieprzem. Całość dobrze mieszamy i odstawiamy do lodówki na około 2 godziny aby się ‚przegryzło’.", "f9.jpg"),
                new Przepis(10, 6, "Dżem truskawkowy", "Truskawki umyć, odszczypułkować. Większe sztuki pokroić ( ja wkładałam cały nóż pionowo do garnka i kroiłam je masowo ). Zasypać cukrem  i odstawić aż puszczą sok – około godziny jeśli jest gorąco 3 jeśli temperatura jest umiarkowana. Sok można wlać ( zostawiając 1/3 całości ) i zapasteryzować w słoikach lub butelkach. (około 20-30 minut od zagotowania). Po czym gotować na wolnym ogniu do odparowania syropu jakieś 2 godziny. Po tym czasie, część truskawek zmiksować ( jeśli się nie rozpadły podczas gotowania ). Wlać sok z cytryny, wsypać pektynę, wymieszać i gotować jeszcze 5 minut. Przelewać do suchych, czystych słoików, najlepiej podgrzanych, zakręcać i odstawiać do góry dnem, do wystudzenia. Tak przygotowany dżem może stać dość długo. Co więcej pektyna zapobiega psuciu się przetworów. Miejcie na uwadze też to, że truskawki mają dużo soku  więc ich ilość znacznie się zmniejszy podczas gotowania. Smacznego!’.", "f10.jpg"),
                new Przepis(11, 4, "Szybki deser truskawkowy", "Truskawki kroimy w ćwiartki serek homogenizowany mieszamy z paroma łyżkami otrębów układamy warstwowo otręby, serek, otręby, truskawki, serek (lub jak kto woli inną kolejność) i zajadamy ;) smakuje genialnie! ;)", "f11.jpg"),
                new Przepis(12, 4, "Koktajl truskawkowy", "Przepis jest stosunkowo prosty. Wkładamy wszystko do jakiegoś naczyńka i miksujemy :) Najlepszy schłodzony. Smacznego!", "f12.jpg"),
                new Przepis(13, 3, "Sałatka z papryki", "Paprykę i cebulę pokroić w drobną kostkę. Pomidory sparzyć, obrać, usunąć gniazda nasienne. Pokroić w kostkę. Wszystkie składniki przełożyć do miski. Dodać majonez oraz śmietanę. Doprawić solą i pieprzem, dokładnie wymieszać.!", "f13.jpg"),
                new Przepis(14, 5, "Iceówka", "Cukierki zalewamy wrzącą wodą i mieszamy aż do całkowitego rozpuszczenia, można dodatkowo lekko podgrzewać żeby przyspieszyć cały proces choć i bez tego po kilku minutach wszystko się ładnie rozpuszcza. Czekamy aż uzyskany syrop całkowicie ostygnie i wlewamy do niego spirytus. Przelewamy iceówke do butelek i odkładamy do lodówki na przynajmniej kilka dni żeby się przegryzła. Można dowolnie zmieniać proporcje składników w zależności od pożądanego kopnięcia oraz stopnia słodkości. Wchodzi świetnie, bez przepitki.", "f14.jpg")
        };
        ArrayList<Przepis> przepiski = new ArrayList<Przepis>();
        for (Przepis przepisx : przepisy) {
            przepiski.add(przepisx);
        }
        Database db = new Database(context, null, null, 1);
        db.insertManyPrzepis(przepiski);
    }

    public void initPrzepisSkladnik() {
        PrzepisSkladnik ps[] = {
                new PrzepisSkladnik(1, 1, "dag", "25"),
                new PrzepisSkladnik(1, 2, "", "1"),
                new PrzepisSkladnik(1, 3, "", ""),
                new PrzepisSkladnik(1, 4, "", ""),
                new PrzepisSkladnik(1, 5, "ząb", "1"),
                new PrzepisSkladnik(1, 6, "", ""),
                new PrzepisSkladnik(1, 7, "", ""),
                new PrzepisSkladnik(2, 10, "łyżki", "3"),
                new PrzepisSkladnik(2, 11, "łyżka", "1"),
                new PrzepisSkladnik(2, 12, "", "1"),
                new PrzepisSkladnik(2, 5, "ząbki", "2"),
                new PrzepisSkladnik(2, 13, "łyżeczka", "1"),
                new PrzepisSkladnik(2, 14, "kg", "1"),
                new PrzepisSkladnik(2, 3, "", ""),
                new PrzepisSkladnik(2, 4, "", ""),
                new PrzepisSkladnik(4, 18, "kg", "" + 1),
                new PrzepisSkladnik(4, 19, "kg", "" + 1),
                new PrzepisSkladnik(4, 3, "łyżka", "" + 1),
                new PrzepisSkladnik(4, 20, "łyżeczka", "" + 1),
                new PrzepisSkladnik(4, 26, "dag", "" + 20),
                new PrzepisSkladnik(4, 21, "dag", "10-15"),
                new PrzepisSkladnik(4, 22, "łyżeczki", "" + 3),
                new PrzepisSkladnik(4, 6, "łyżki", "" + 2),
                new PrzepisSkladnik(4, 23, "szklanki", "" + 2),
                new PrzepisSkladnik(4, 24, "łyżeczka", "" + 1 / 2),
                new PrzepisSkladnik(4, 25, "dag", "" + 20),
                new PrzepisSkladnik(4, 4, "", ""),
                new PrzepisSkladnik(5, 2, "", "" + 3),
                new PrzepisSkladnik(5, 6, "", "" + 14),
                new PrzepisSkladnik(5, 20, "płaskie łyżki", "" + 2),
                new PrzepisSkladnik(5, 3, "łyżeczki", "" + 1 / 2),
                new PrzepisSkladnik(5, 27, "łyżki", "" + 2),
                new PrzepisSkladnik(5, 28, "dag", "" + 20),
                new PrzepisSkladnik(5, 29, "krople", "5-6"),
                new PrzepisSkladnik(5, 8, "", ""),
                new PrzepisSkladnik(6, 30, "g", "" + 10),
                new PrzepisSkladnik(6, 20, "łyżka", "" + 1),
                new PrzepisSkladnik(6, 6, "kg", "" + 1),
                new PrzepisSkladnik(6, 31, "szklanki", "" + 3),
                new PrzepisSkladnik(6, 32, "szklanka", "" + 1),
                new PrzepisSkladnik(6, 8, "łyżek", "" + 8),
                new PrzepisSkladnik(6, 3, "", ""),
                new PrzepisSkladnik(6, 33, "szklanki", "3/4"),
                new PrzepisSkladnik(6, 34, "łyżeczki", "" + 2),
                new PrzepisSkladnik(7, 35, "g", "300"),
                new PrzepisSkladnik(7, 2, "", "2"),
                new PrzepisSkladnik(7, 27, "łyżki", "2"),
                new PrzepisSkladnik(7, 21, "", "1"),
                new PrzepisSkladnik(7, 22, "łyżka", "" + 1),
                new PrzepisSkladnik(7, 8, "łyżki", "2"),
                new PrzepisSkladnik(7, 3, "", ""),
                new PrzepisSkladnik(7, 4, "", ""),
                new PrzepisSkladnik(8, 21, "kg", "1"),
                new PrzepisSkladnik(8, 36, "l", "2"),
                new PrzepisSkladnik(8, 5, "ząbki", "2"),
                new PrzepisSkladnik(8, 3, "", ""),
                new PrzepisSkladnik(8, 4, "", ""),
                new PrzepisSkladnik(8, 37, "kromki", "4"),
                new PrzepisSkladnik(8, 4, "tarty", ""),
                new PrzepisSkladnik(9, 38, "mały", "1"),
                new PrzepisSkladnik(9, 5, "ząbków", "2-4"),
                new PrzepisSkladnik(9, 3, "", ""),
                new PrzepisSkladnik(9, 4, "", ""),
                new PrzepisSkladnik(10, 39, "op.", "2"),
                new PrzepisSkladnik(10, 20, "g", "700"),
                new PrzepisSkladnik(10, 12, "szt", "1"),
                new PrzepisSkladnik(10, 28, "kg", "4"),
                new PrzepisSkladnik(11, 28, "szt", "4"),
                new PrzepisSkladnik(11, 40, "", ""),
                new PrzepisSkladnik(11, 41, "", ""),
                new PrzepisSkladnik(12, 28, "kg", "1"),
                new PrzepisSkladnik(12, 38, "l", "0.5-1"),
                new PrzepisSkladnik(12, 20, "łyżki", "1-2"),
                new PrzepisSkladnik(13, 42, "szt", "1"),
                new PrzepisSkladnik(13, 43, "szt", "2"),
                new PrzepisSkladnik(13, 21, "szt", "1"),
                new PrzepisSkladnik(13, 15, "łyżki", "2"),
                new PrzepisSkladnik(13, 27, "łyżki", "2"),
                new PrzepisSkladnik(13, 3, "", ""),
                new PrzepisSkladnik(13, 4, "", ""),
                new PrzepisSkladnik(14, 44, "l", "0.4"),
                new PrzepisSkladnik(14, 42, "l", "0.5"),
                new PrzepisSkladnik(14, 42, "g", "300"),
        };
        ArrayList<PrzepisSkladnik> s = new ArrayList<PrzepisSkladnik>();
        for (PrzepisSkladnik p_s : ps)
            s.add(p_s);
        Database db = new Database(context, null, null, 1);
        db.addManyPrzepisSkladnik(s);
    }

    public void initKategorie() {
        ArrayList<Kategoria> k = new ArrayList<Kategoria>();
        Kategoria[] tt = {
                new Kategoria("Danie główne", "kat1"),
                new Kategoria("Zupy", "kat2"),
                new Kategoria("Sałatki i surówki", "kat3"),
                new Kategoria("Desery", "kat4"),
                new Kategoria("Studenckie", "kat5"),
                new Kategoria("Inne", "kat6")
        };
        for (Kategoria s : tt)
            k.add(s);
        Database db = new Database(context, null, null, 1);
        db.insertManyKategorie(k);
    }


}
