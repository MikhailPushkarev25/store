CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  name TEXT,
  username TEXT,
  email TEXT,
  password TEXT
);

CREATE TABLE post(
  id SERIAL PRIMARY KEY,
  name TEXT
);

insert into post(name) values('apple');
insert into post(name) values('auto');
insert into post(name) values('fruit');
insert into post(name) values('Honor');
insert into post(name) values('Sumsung');
select * from post;
insert into users(name, username, email, password) values('Mike', 'Pushkarev', 'prom@mail.ru', '123');
select * from users;
