create table cats
(
    id serial not null
        constraint cat_key
            primary key,
    name varchar(255),
    color varchar(255),
    multi_color boolean
);

alter table cats owner to postgres;

create table kitties
(
    id serial not null
        constraint kitty_key
            primary key,
    name varchar(255) not null,
    color varchar(255),
    cat_id bigint not null
        constraint fk_cats_kitties_id
            references cats
);

alter table kitties owner to postgres;

