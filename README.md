 Przepisy 
==============

Projekt Książki Kucharskiej wykonywanej w ramach kursu Projekt Zespołowy 1 i 2 na Uniwersytecie Jagiellońskim. Program jest aplikacją przeznaczoną dla interfejsu Metro ('kafelkiem') dla systemu Windows 8.1

-------------------------------

### Wykorzystane Technologie : ###

* Baza Danych:
	* SQLite3
* Aplikacja Kliencka:
	* HTML5 (GUI),
	* CSS (GUI),
	* JavaScript (GUI)
* Aplikacje Pomocnicze:
	* Java (Program do zbierania przepisów)
* Pomocnicze : 
	* Git (System kontroli wersji)   
	* Wrapper do SQlite 3 ([Sqlite3-WinRT](https://github.com/doo/SQLite3-WinRT "Sqlite3-WinRT"))
	* Microsoft Visual Studio Professional (lub Ultimate ) 2013.
	
### Organizacja : 

* Git :
	* Na branchu master jest wersja programu którą można bezpiecznie pobierać i rozwijać.
	* Na branchu develop jest wersja programu nad którą aktualnie pracujemy, może być niestabilna.
* Katalogi:
	* doc -> Zawiera Dokumentacje Projektu.
	* src -> Zawiera cały kod projektu.
		*  database -> zawiera kod i treści związane z aplikacją zbierającą przepisy.
		*  App3 -> Zawiera całą solucje projektu przygotowaną pod Microsoft Visual Professional 2013.
	
### Autorzy Projektu (alfabetycznie):
	Bartosz Bargiel <bargl1991@gmail.com>
	Piotr Kruk <ppiotr.kruk@uj.edu.pl>
	Marcin Milik <marcin.milik@uj.edu.pl>
	Piotr Rachwał <piotr.rachwal27@gmail.com>

---



1. Założenie Projektu.
-----------------------

### 1.1. Cel.

Program ma służyć jako książka kucharska z intuicyjnym i przejrzystym interfejsem.

### 1.2. Główne założenia. 

* Możliwość przeglądania przepisów kategoriami jak i alfabetycznie
* Dodawanie Przepisów przez użytkownika.
* Usuwanie Przepisów przez użytkownika.
* Wyszukiwanie Przepisów po nazwie.
* Wyszukiwanie Przepisów po składnikach.

### 1.3. Możliwości.

Wszystkie powyższe założenia projektu zostały dokonane przy zamknięciu trzeciego milestone "Wyszukiwarka".

### 1.4. Ograniczenia przyjęte przy projektowaniu.

* Pojemność bazy przepisów nie może być większa niż 60, powyżej tej liczby, program może zwalniać, jest to związane z ograniczeniami SQLite3. 
* Wersja Programu jest przeznaczona wyłacznie dla Windows 8.1. Związane jest to z wsparciem Microsoftu dla Javascriptu.

### Użytkowanie i Rozwijanie Projektu :

Do poprawnego użytkowania i rozwijanie projektu potrzebujemy : 

* System Windows 8.1
* Microsoft Visual Studio Professional 2013
* Git
* SQLite for Windows Runtime (Windows 8.1)
* .NET 4.5 >=

Do ściągnięcia i rozwijania projektu potrzebujemy tylko i wyłącznie sklonować lub ściągnąć to repozytorium i otworzenia solucji w Microsoft Visual Studio Professional 2013 z katalogu src.


-------------------------------------------------

## Licencja : 


Copyright (C)

* 	Bartosz Bargiel < <bargl1991@gmail.com> >
*	Piotr Kruk < <ppiotr.kruk@uj.edu.pl> >
*	Marcin Milik < <marcin.milik@uj.edu.pl> >
*	Piotr Rachwał < <piotr.rachwal27@gmail.com>	>

Niniejszy program jest wolnym oprogramowaniem - możesz go rozpowszechniać dalej
i/lub modyfikować na warunkach Powszechnej Licencji Publicznej GNU wydanej przez
Fundację Wolnego Oprogramowania, według wersji 3 tej Licencji lub dowolnej
z późniejszych wersji.

Niniejszy program rozpowszechniany jest z nadzieją, iż będzie on użyteczny - jednak
BEZ ŻADNEJ GWARANCJI, nawet domyślnej gwarancji PRZYDATNOŚCI HANDLOWEJ
albo PRZYDATNOŚCI DO OKREŚLONYCH ZASTOSOWAŃ. Bliższe informacje na ten temat
można uzyskać z Powszechnej Licencji Publicznej GNU.

Kopia Powszechnej Licencji Publicznej GNU powinna zostać ci dostarczona razem
z tym programem. Jeżeli nie została dostarczona, odwiedź http://www.gnu.org/licenses.

Nieoficjalne, tłumaczenie licencji znajdziesz pod adresem: [License](http://itlaw.computerworld.pl/index.php/gpl-3/)

