INSERT into users (username, password)
VALUES ('User1', '$2a$12$93cG0JGu6jw06sEq2bW3yezg9obYKIFOhcbLhxUDMYhu75dn78cSy');

INSERT into users (username, password)
VALUES ('User2', '$2a$12$93cG0JGu6jw06sEq2bW3yezg9obYKIFOhcbLhxUDMYhu75dn78cSy');

INSERT into roles (name)
Values ('USER');

INSERT into Users_roles (user_id, roles_id)
VALUES (1, 1);

INSERT into Users_roles (user_id, roles_id)
VALUES (2, 1);

