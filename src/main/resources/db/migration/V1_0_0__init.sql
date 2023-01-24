create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create table app_user
(
    id int8 not null,
    email varchar(90) not null,
    name varchar(90) not null,
    password varchar(250) not null,
    primary key (id),
    unique (email)
);

create table role
(
    id int8 not null,
    name varchar(60) not null,
    primary key (id),
    unique (name)
);

create table app_user_roles
(
    app_user_entity_id int8 not null,
    roles_id int8 not null,
    unique (app_user_entity_id, roles_id)
);

alter table app_user_roles
    add constraint role_fk foreign key (roles_id)
        references role;

alter table app_user_roles
    add constraint app_user_fk foreign key (app_user_entity_id)
        references app_user;


