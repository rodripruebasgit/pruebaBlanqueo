-- Query para consultar los citizen de nivel 3 en Login2.
-- Verificar si LEVEL_TYPE_ID cambia por ambiente.
-- tiene filtro por fecha desde hasta
SELECT c.FIRST_NAME , c.LAST_NAME  , MDT.SHORT_NAME , c.DOCUMENT_NUMBER , mg.SHORT_NAME, ce.EMAIL, cl.INSERTED_ON
FROM CITIZEN c
    INNER JOIN CITIZEN_LEVELS cl ON (c.CITIZEN_ID = cl.CITIZEN_ID)
    INNER JOIN CITIZEN_EMAILS ce ON (c.CITIZEN_ID = ce.CITIZEN_ID)
    INNER JOIN MASTER_GENDER mg ON (c.GENDER_ID  = mg.GENDER_ID)
    INNER JOIN MASTER_DOCUMENT_TYPE mdt ON (c.DOC_TYPE_ID = mdt.DOC_TYPE_ID  )
WHERE cl.LEVEL_TYPE_ID = 3 AND cl.DELETED_ON IS NULL
    AND cl.INSERTED_ON >= to_timestamp('18-11-2021 00:00:00', 'dd-mm-yyyy hh24:mi:ss')
    AND cl.INSERTED_ON <= to_timestamp('22-11-2021 23:59:59', 'dd-mm-yyyy hh24:mi:ss')
ORDER BY cl.INSERTED_ON desc
