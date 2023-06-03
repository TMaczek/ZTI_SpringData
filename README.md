# ZTI - Seminarium SpringData

## Pobieranie
Aby uruchomić przykład klonujemy gita, otwieramy w IntelliJ, który pobierze nam wszystkie potrzebne dependencje.

## Set-Up bazy danych

W pliku **src/main/resources/application.properties** dodajemy własne dane do uwierzytelnienia do bazy danych Postres.

Jeśli nie chcemy tworzyć bazy w chmurze, możemy użyć lokalnej H2 zmieniając w ``application.properties``:
 - ``spring.datasource.driverClassName`` na ``org.h2.Driver``
 - ``spring.datasource.url`` na ``jdbc:h2:file:./myDB``, gdzie ``./myDB`` możemy zastąpić dowolną ścieżką do bazy
 - użytkownik i hasło mogą być dowolne, potrzebne są jedynie do **h2 console**, dla których należy dodać:
  ``spring.h2.console.enabled=true`` oraz ``spring.h2.console.path=/h2-console`` (lub inny adres).
  
 Za zmianę ustawień tworzenia bazy odpowiada ``spring.jpa.hibernate.ddl-auto`` (tutaj mamy ``update``, by nie usuwać rekordów za każdym uruchomieniem).
 
 
 ## Struktura projektu
**Entity** - encje, każda zawiera pola `id` oraz `name` oraz inne reprezentujące relacje.
- **Student** - zawiera `courses` czyli listę kursów, na które student jest zapisany,
- **Teacher** - zawiera `courses` - listę kursów, które nauczyciel prowadzi,
- **Course** - `students` to lista studentów uczęszczających na kurs, `teacher` to nauczyciel prowadzący.
  Warto zacznaczyć, że dla kursu prowadzący będzie w bazie kluczem obcym, podczas gdy powiązanie student-kurs jest reprezentowane w tabeli asocjacyjnej `student_course` (argument adnotacji `@JoinTable`).
  
  Dla każdej z tych klas stworzone zostało **repository**, które dostarcza metod do operacji na konkretnej encji. Repozytoria są wstrzyknięte do klasy ``TestController``, która definiuje endpointy aplikacji.
  
 ## Endpointy
 Po uruchomieniu ``SpringDataPrezentacjaApplication`` będziemy mieć dostępne następujące endpointy zdefiniowane w ``TestController``
 
 
| Endpoint  | Typ | Opis|
| ------------- | ------------- | ------------- |
| `/student/{id}`  | GET  | Pobranie studenta po Id  |
| `/teacher/{id}`  | GET  | Pobranie nauczyciela po Id  |
| `/course/{id}`  | GET  | Pobranie kursu po Id  |
| `/student`  | GET  | Pobranie wszystkich studentów  |
| `/teacher`  | GET  | Pobranie wszystkich nauczycieli  |
| `/course`  | GET  | Pobranie wszystkich kursów  |
| `/student/namelike/{str}` | GET | Znalezienie studenta mającego **str** w `name`|
| `/course/nullteacher` | GET | Znalezienie kursów bez przypisanego nauczyciela |
| `/student/custom/jpql/{student_id}`| GET | Pobranie studenta po id za pomocą Query w JPQL|
| `/student/custom/native/{student_id}`| GET |Pobranie studenta po id za pomocą Query natywnego|
| `/student`| POST | Dodanie studenta |
| `/teacher`| POST | Dodanie nauczyciela |
| `/course`| POST | Dodanie kursu |
| `/student/{id}`| DELETE | Usunięcie studenta o danym id |
| `/teacher/{id}`| DELETE | Usunięcie nauczyciela o danym id |
| `/course/{id}`| DELETE | Usunięcie kursu o danym id |
| `/student`| DELETE | Usunięcie wszystkich studentów |
| `/teacher`| DELETE | Usunięcie wszystkich nauczycieli |
| `/course`| DELETE | Usunięcie wszystkich kursów |
| `/course/{course_id}/student/{student_id}`| PUT |Dodanie do kursu o id **course_id** studenta o id **student_id**|
| `/course/{course_id}/teacher/{teacher_id}`| PUT |Dodanie do kursu o id **course_id** nauczyciela o id **teacher_id**|

## Inne
Niniejszy kod prezentuje jedynie pokrótce co możemy osiągnąć dzięki użyciu SpringData. Nie jest on w żadnym wypadku pełnym kodem aplikacji.
