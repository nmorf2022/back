drop table app_user_roles;

drop table role;

alter table app_user
    add column role varchar(60) not null default 'USER',
    add column user_status varchar(60) not null default 'ACTIVE';