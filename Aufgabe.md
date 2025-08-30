#Refactoring using the ObjectCalisthenics rules from Jeff Bay
## Einleitung
Die Klasse /tennis-kata/src/main/java/com/cgi/tennis/ToBeRefactored.java enthält eine Implementierung der Zählweise eines Spiels im Tennsis. Leider hatte der Entwickler, im Gegensatz zu euch, nicht die Lesbarkeit des Quellcodes im Fokus, was nun korrgiert werden soll.
## Aufgabe
Jeff Bay hat sich Gedanken gemacht welche Kriterien für Ihn entscheidend sind um gutes Design und Lesbarkeit von Code herzustellen. (/tennis-kata/DevelopersAnonymous-ObjectCalisthenics.pdf) Das Ziel dieser Aufgabe ist es, die Implementierung des Tennisspiels in Schritten so anzupassen, das sie möglichst viele Regeln von Jeff anwenden.
## Vorgehen
Um sich beim Refactoring davor zu schützen, das man in einem Schritt die Implementierung kaputt macht, deckt der UnitTest /tennis-kata/src/test/java/com/cgi/tennis/TennisTest.java alle Grenzfälle des Spiels ab und kann als vollständig betrachtet werden. Der UnitTest sucht im Package com.cgi.tennis nach implementierungen vom interface TennisGame und testet diese.
## Bewertung
Um die Nachvollziehbarkeit eurer Schritte für die Bewertung zu gewährleisten, legt so viele Kopien vom Tennis spiel an und nennt diese Step1, Step2, usw. Bitte dokumentiert ca. 10 Schritte. Alternativ könnt Ihr auch Github o.ä. verwenden und die Schritte regelmässig pushen.
Versucht möglichst viele der Regeln von Jeff umzusetzen um den Code lesbarer und verständlicher zu machen. Falls euch noch Packages/Classen zum Struktierern helfen, könnt ihr sie natürlich anlegen. Wichtig ist, das bei jedem eurer Schritte die Tests grün bleiben.
Die Wahl der IDE spielt keine Rolle.