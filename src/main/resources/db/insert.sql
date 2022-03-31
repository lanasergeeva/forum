insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('master', true, '$2a$10$cKiygpsLdSOGI/sFHJwjLe2t68jIcOJfgmfAzTg1Xm4aPQwYzyXji',
        (select id from authorities where authority = 'ROLE_ADMIN'));

insert into users (username, enabled, password, authority_id)
values ('max', true, '$2a$10$x28/K4COatN0NESYeXvOkelZp7Mjr0Khetu9c5hg9M/uBnBKavZb2',
        (select id from authorities where authority = 'ROLE_USER'));


insert into posts (name, description, user_id)
values ('СП iHerb', 'Делаю заказы с ihreb', 2);
insert into posts (name, description, user_id)
values ('Продам машину', 'Быстрым - скидка!', 1);
insert into posts (name, description, user_id)
values ('Походы выходного дня. Джип туры.', 'Занимаюсь организацией походов по горам Крыма', 2);

insert into replies (text, user_id, post_id)
values ('Подпишусь', 1, 1);
insert into replies (text, user_id, post_id)
values ('Не могу довзониться', 2, 2);
insert into replies (text, user_id, post_id)
values ('Перекуп. Не связывайтесь.', 2, 2);