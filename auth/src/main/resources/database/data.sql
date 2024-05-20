DELETE
FROM
    USER
WHERE
    user_id = 1;

INSERT
    INTO
        USER(
            user_id,
            user_name
        )
    VALUES(
        1,
        'Test User'
    );
