create table users (id bigserial not null primary key, password varchar(255), username varchar(255));
create unique index ix_users_username on users (username);
create table authorities (user_id bigint not null, authority varchar(255), constraint fk_authorities_users foreign key(user_id) references users(id));
create unique index ix_authorities on authorities (user_id,authority);

insert into users(username, password) values ('user', '$2a$10$ODMwT1Lmkz4ssV540FK.Q.638SkBBnJ3YFuCpU14rb8mYRIVYQ/Te');
insert into authorities(user_id, authority) values ((select id from users where username = 'user'), 'ROLE_USER')
