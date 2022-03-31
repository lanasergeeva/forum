CREATE TABLE authorities
(
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users
(
    id           serial primary key,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(100) NOT NULL,
    enabled      boolean default true,
    authority_id int          not null,
    foreign key (authority_id) references authorities(id)
);

create table posts
(
    id          serial primary key,
    name        varchar(2000),
    description text,
    created     timestamp without time zone not null default now(),
    user_id     int not null,
    foreign key (user_id) references users (id)
);

CREATE TABLE replies
(
    id      serial primary key,
    created timestamp without time zone,
    text    character varying(255),
    post_id int not null,
    user_id int not null,
    foreign key (post_id) references posts(id),
    foreign key (user_id) references users(id)
);



