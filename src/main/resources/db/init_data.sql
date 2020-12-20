insert into cats(name, color, multi_color) values ('Ms norris', 'Yellow', true);
insert into cats(name, color, multi_color) values ('Snow', 'white', false);

insert into kitties(name, color, cat_id) values ('Smallcie', 'grey', 1);
insert into kitties(name, color, cat_id) values ('Streepy', 'black', 2);











insert into roles(id, name) values (1, 'ADMIN');
insert into roles(id, name) values (2, 'DEFAULT');
insert into roles(id, name) values (3, 'MANAGER');

-- admin/admin
insert into users(id, name, password) values (1, 'admin', '$2y$10$UYoqdGVd.n5cR8AE1k9zxuIUdwzZXdKz3I0BEEZF3/oX4XSQSBQQi');
-- pdefault/ defaultpassword
insert into users(id, name, password) values (2, 'pdefault', '$2y$10$YiJSwYmRy3BI0UMzVE4gh.GZocjHk9lz2/cJ25rO6C6dzMgH6QARe');

-- Assing ADMIN and DEFAULT to admin user
insert into users_roles(user_id, role_id) VALUES (1, 1);
insert into users_roles(user_id, role_id) VALUES (1, 2);

-- Assign DEFAULT to pdefault
insert into users_roles(user_id, role_id) VALUES (2, 2);
