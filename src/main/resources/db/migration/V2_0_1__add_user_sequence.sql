create sequence if not exists app_user_id_seq increment by 1 start with 1;

alter table app_user
    drop column id,
    add column id int8 not null default nextval('app_user_id_seq'::regclass);