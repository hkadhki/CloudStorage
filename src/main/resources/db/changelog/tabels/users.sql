create table roles (id  bigserial not null, name varchar(255) not null, primary key (id));
create table users (id  bigserial not null, password varchar(255) not null, username varchar(255) not null, primary key (id));
create table users_roles (user_id int8 not null, roles_id int8 not null);