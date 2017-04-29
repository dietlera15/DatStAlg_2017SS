Hashing - Algorithmen

Aufgabenbeschreibung:
---------------------
Implementieren Sie den Double-Hashing und Double-Hashing mit Brent Algorithmus um Objekte in einer Hashtabelle zu organisieren


Hinweise zur Implementierung:
----------------------------

    * Die Hashtabelle ist ein Array von HashObject Objekten, die die eigentlichen Datenobjekte beinhalten können.
    * Ist das HashObject in einer Tabelleposition "null", ist der Tabellenplatz leer (Suche ist zu Ende, der Tabellenplatz steht zum Einfügen zur Verfügung).
    * Ist an der Tabellenposition ein gültiges HashObject, aber der Dateninhalt des HashObject Objectes ist "null", dann ist der Tabellenplatz
      "frei", d.h. das Element an dieser Position wurde gelöscht (Suche geht weiter, der Platz steht aber zum Einfügen zur Verfügung).
    * Die standard Java Object Methode "hashCode()" wird verwendet, um den "key" Wert der gespeicherten Objekte zu ermitteln


ToDo:
====

1) Importieren des Projektes in die IDE

2) Ausimplemtieren der unvollständigen Methoden (Methoden-Stubs in Klasse vorhanden)

3) Testen der Implementierung durch ausführen der Klasse HashingTest.java oder HashingTestRunner.java

4) Upload der Datei HashTable.java in den Abgabeordner der Lehrveranstaltung


