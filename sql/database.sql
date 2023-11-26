create table candidat
(
    email       varchar(255) not null
        primary key,
    city        varchar(255),
    complement  varchar(255),
    country     varchar(255),
    street      varchar(255),
    street_num  varchar(255),
    zip_code    varchar(255),
    birth_date  timestamp,
    citizenship varchar(255),
    cv          varchar(255),
    etat        varchar(255),
    first_name  varchar(255),
    gender      integer      not null,
    last_name   varchar(255),
    phone       varchar(255),
    photo       varchar(255),
    short_bio   varchar(255)
);

alter table candidat
    owner to postgres;

create table disponibilite
(
    id             bigserial
        primary key,
    ends_at        timestamp,
    job_category   varchar(255),
    starts_at      timestamp,
    candidat_email varchar(255)
        constraint fkro4qgwi7x0is5nir8jp9k8nlf
            references candidat
);

alter table disponibilite
    owner to postgres;

create table disponibilite_places
(
    disponibilite_id bigint not null
        constraint fkmr8ijfa8sodxlo7spied7g47a
            references disponibilite,
    place            varchar(255)
);

alter table disponibilite_places
    owner to postgres;

create table etablissement
(
    id                 bigserial
        primary key,
    city               varchar(255),
    complement         varchar(255),
    country            varchar(255),
    street             varchar(255),
    street_num         varchar(255),
    zip_code           varchar(255),
    establishment_name varchar(255)
);

alter table etablissement
    owner to postgres;

create table experience
(
    id               bigserial
        primary key,
    ended_at         timestamp,
    job              varchar(255),
    job_category     varchar(255),
    started_at       timestamp,
    etablissement_id bigint
        constraint fk57cb66nqufbf8p3q86le7lg8f
            references etablissement,
    candidat_email   varchar(255)
        constraint fkdlty6n516ocp7vb597steovkg
            references candidat
);

alter table experience
    owner to postgres;

create table opinion
(
    id             bigserial
        primary key,
    employer_id    bigint,
    message        varchar(255),
    provided_at    timestamp,
    score          double precision not null,
    candidat_email varchar(255)
        constraint fkbu432nluu7pwq7bgch5k3ums1
            references candidat
);

alter table opinion
    owner to postgres;

create table reference
(
    id                bigserial
        primary key,
    city              varchar(255),
    complement        varchar(255),
    country           varchar(255),
    street            varchar(255),
    street_num        varchar(255),
    zip_code          varchar(255),
    ref_email         varchar(255),
    ref_establishment varchar(255),
    ref_name          varchar(255),
    ref_phone         varchar(255),
    candidat_email    varchar(255)
        constraint fkcachciox8o9s332svjh3eumt6
            references candidat
);

alter table reference
    owner to postgres;

