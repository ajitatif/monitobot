create table api_keys (
    `key` varchar(63) primary key,
    enabled bool not null default true,
    created_on_utc timestamp without time zone not null default now(),
    updated_on_utc timestamp without time zone not null default now(),
    update_comment text
);

insert into api_keys (`key`, update_comment) values ('MHN0czkwWjgjYkJt', 'initial password for remote connections');