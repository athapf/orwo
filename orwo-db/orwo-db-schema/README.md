
1. Properties

Es gibt 3 überlagerte Properties: als Resource im Packet, im Projekt-Verzeichnis (über $PROJECT_HOME) und im Home-Verzeichnis des Benutzers.
Name der Property-Datei: develop.properties. Property-Datei jeweils im angegebenen Verzeichnis (keine Unterverzeichnisse).

2. Modul 'Schema' (nur für Entwicklungsumgebung und Tests)

Enthält die komplette Entwicklung des Datenbank-Schemas mit Flyway und ein DbUnit-Export des Anfangszustand.
Auch weitere vollständige Testdaten-Dumps können ergänzt sein.

Library mit DatabaseManager und Methoden für Erstellen und Migrieren mit Flyway sowie initialisieren der Datenbank mit DbUnit.
- migrate (migrieren der DB zum aktuellen Stand)
- cleanAndMigrate (komplettes löschen und neu aufsetzen der Datenbank)
- initialize (Anfangszustand der Datenbank mit DbUnit CLEAN_INSERT einspielen)
- initialize(data-file, data-file, ...) (initialisieren und angegebene DbUnit-Files mit REFRESH einspielen)
