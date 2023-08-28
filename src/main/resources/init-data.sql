INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER'), (3, 'ROLE_GUEST');

INSERT INTO users (email, name, last_name, password)
VALUES
    ('user@bk.ru', 'user', 'Ivanov', '$2a$10$vWNINPp6Zkg1o8TzKxYo0OAp.zMxQAfnYxEN.MS0grFDScriDuuQq'),
    ('admin@bk.ru', 'admin', 'Rublev', '$2a$10$TiriPk423pd21TuJritLXemrqRxXWACcYaH8PixshuLoa0z90PSP6');

INSERT INTO users_roles (users_id, roles_id)
VALUES
    (1, 2),
    (2, 1);