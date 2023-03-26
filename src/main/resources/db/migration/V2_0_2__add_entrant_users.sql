alter table app_user
    add column dtype varchar(31) not null default 'APP_USER',
    add column driver_category varchar(255);
