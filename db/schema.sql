CREATE SEQUENCE clients_id_seq AS BIGINT;

create table clients
(
    id            bigint       not null default nextval('clients_id_seq'),
    name         varchar(255) not null,
    email        varchar(255) not null unique,
    api_key      varchar(512) not null unique,
    issuer_info  varchar(255) not null,
    permissions  text array,
    allowed_ips  text array,
    issued_at    timestamp,
    request_count bigint,
    expires_at   timestamp
);
