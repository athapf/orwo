
*** Keywords ***

Open test browser
    Open browser  about:

Close test browser
    Close all browsers

OverviewPage should be shown
    Page should contain element  css=*[id$='maskName']
    Page should contain element  css=*[id$='version']
    Element should contain  css=*[id$='maskName']  Overview

Remove all notes
    Connect To Database    org.postgresql.Driver    jdbc:postgresql://localhost:25432/orwo    orwo    orwo
    Execute SQL    DELETE FROM T_NOTE;
    Execute SQL    COMMIT;
    Disconnect From Database

Insert 3 notes
    Connect To Database    org.postgresql.Driver    jdbc:postgresql://localhost:25432/orwo    orwo    orwo
    Execute SQL    INSERT INTO t_note (id, timestamp, content) VALUES (201, '2015-09-08 14:49:37.835000', 'a table');
    Execute SQL    INSERT INTO t_note (id, timestamp, content) VALUES (202, '2015-09-08 14:41:42.134000', 'a chair');
    Execute SQL    INSERT INTO t_note (id, timestamp, content) VALUES (203, '2015-09-08 14:34:24.612000', 'an umbrella');
    Execute SQL    COMMIT;
    Disconnect From Database