
*** Settings ***
Library    Selenium2Library
Library    OperatingSystem
Library    DatabaseLibrary

Resource   ../resources/keywords.robot

Force Tags  smoke  first

Test Setup       Open test browser
Test Teardown    Close All Browsers


*** Variables ***

${LOGIN_FAIL_MSG}  Falsches Passwort


*** Test Cases ***

Start Application
    Go to    http://localhost:8086/orwo

    Title Should Be    ORWO


Notes Overview Page with 3 notes listed
    Remove all notes
    Insert 3 notes

    Go to    http://localhost:8086/orwo

    OverviewPage should be shown
