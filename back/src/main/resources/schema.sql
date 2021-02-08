create table IF NOT EXISTS users(
    username varchar(50) primary key,
    password varchar(500),
    enabled boolean
);

create table IF NOT EXISTS authorities (
    username varchar(50),
    authority varchar(50),
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index IF NOT EXISTS ix_auth_username on authorities (username,authority);