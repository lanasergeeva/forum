create table posts
(
    id          serial primary key,
    created     timestamp without time zone,
    description character varying(255),
    user_id references users (id) not null
);


CREATE TABLE replies
(
    id      serial primary key,
    created timestamp without time zone,
    text    character varying(255),
    post_id integer references posts (id) not null,
    user_id integer references users (id) not null
);

