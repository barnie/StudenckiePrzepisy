 Przepisy 
==============

Projekt Książki Kucharskiej wykonywanej w ramach kursu Projekt Zespołowy 1 i 2 na Uniwersytecie Jagiellońskim. Program jest aplikacją przeznaczoną dla interfejsu Metro ('kafelkiem') dla systemu Windows 8.1

-------------------------------

### Wykorzystane Technologie: ###

* Baza Danych:
	* SQLite3
* Aplikacja Kliencka:
	* HTML5 (GUI)
	* CSS (GUI)
	* JavaScript (GUI)
* Aplikacje Pomocnicze:
	* Java (Program do zbierania przepisów)
* Pomocnicze : 
	* Git (System kontroli wersji)
	* Wrapper do SQlite 3 ([Sqlite3-WinRT](https://github.com/doo/SQLite3-WinRT "Sqlite3-WinRT"))
	* Microsoft Visual Studio Professional (lub Ultimate ) 2013
	
### Organizacja: 

* Git :
	* Na branchu master jest wersja programu, którą można bezpiecznie pobierać i rozwijać
	* Na branchu develop jest wersja programu, nad którą aktualnie pracujemy. Może być niestabilna
* Katalogi:
	* doc -> Zawiera dokumentacje projektu
	* src -> Zawiera cały kod projektu
		*  database -> zawiera kod i treści związane z aplikacją zbierającą przepisy
		*  App3 -> zawiera całą solucje projektu przygotowaną pod Microsoft Visual Professional 2013
	
### Autorzy Projektu (alfabetycznie):
	Bartosz Bargiel <bargl1991@gmail.com>
	Piotr Kruk <ppiotr.kruk@uj.edu.pl>
	Marcin Milik <marcin.milik@uj.edu.pl>
	Piotr Rachwał <piotr.rachwal27@gmail.com>

---



1. Założenie Projektu
-----------------------

### 1.1. Cel

Program ma służyć jako książka kucharska z intuicyjnym i przejrzystym interfejsem.

### 1.2. Główne założenia 

* Możliwość przeglądania przepisów kategoriami jak i alfabetycznie
* Dodawanie przepisów przez użytkownika
* Usuwanie przepisów przez użytkownika
* Wyszukiwanie przepisów po nazwie
* Wyszukiwanie przepisów po składnikach

### 1.3. Możliwości

Wszystkie powyższe założenia projektu zostały dokonane przy zamknięciu trzeciego milestone "Wyszukiwarka".

### 1.4. Ograniczenia przyjęte przy projektowaniu

* Pojemność bazy przepisów nie może być większa niż 60, powyżej tej liczby, program może zwalniać, jest to związane z ograniczeniami SQLite3.
* Wersja Programu jest przeznaczona wyłacznie dla Windows 8.1. Związane jest to z wsparciem Microsoftu dla Javascriptu

### Użytkowanie i Rozwijanie Projektu:

Do poprawnego użytkowania i rozwijanie projektu potrzebujemy: 

* System Windows 8.1
* Microsoft Visual Studio Professional 2013
* Git
* SQLite for Windows Runtime (Windows 8.1)
* .NET 4.5 >=

Jeśli chcesz rozwijać ten program lub z niego korzystać, wystarczy, że ściągniesz lub sklonujesz nasze repozytorium 
i otworzysz solucje (która znajduje się w katalogu src) w Microsoft Visual Studio Professional 2013

-------------------------------------------------

## Licencja: 


Copyright (C)

* 	Bartosz Bargiel < <bargl1991@gmail.com> >
*	Piotr Kruk < <ppiotr.kruk@uj.edu.pl> >
*	Marcin Milik < <marcin.milik@uj.edu.pl> >
*	Piotr Rachwał < <piotr.rachwal27@gmail.com>	>

Niniejszy program jest wolnym oprogramowaniem - możesz go rozpowszechniać dalej
i/lub modyfikować na warunkach Powszechnej Licencji Publicznej GNU wydanej przez
Fundację Wolnego Oprogramowania, według wersji trzeciej tej Licencji lub dowolnej
z późniejszych wersji.

Niniejszy program rozpowszechniany jest z nadzieją, iż będzie on użyteczny - jednak
BEZ ŻADNEJ GWARANCJI, nawet domyślnej gwarancji PRZYDATNOŚCI HANDLOWEJ
albo PRZYDATNOŚCI DO OKREŚLONYCH ZASTOSOWAŃ. Bliższe informacje na ten temat
można uzyskać z Powszechnej Licencji Publicznej GNU.

Kopia Powszechnej Licencji Publicznej GNU powinna zostać ci dostarczona razem
z tym programem. Jeżeli nie została dostarczona, odwiedź http://www.gnu.org/licenses.

Nieoficjalne tłumaczenie licencji znajdziesz pod adresem: [License](http://itlaw.computerworld.pl/index.php/gpl-3/)

