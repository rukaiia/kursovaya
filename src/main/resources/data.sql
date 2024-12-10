create table if not exists users
(
    id       int auto_increment primary key,
    name     varchar(50),
    password varchar(50)
);

insert into users(name, password)
values ('John Doe', '123456'),
       ('Jane Doe', '654321');