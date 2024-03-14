create table sources (
                         id          bigserial primary key,
                         name        varchar(100) not null,
                         created_at  timestamp with time zone not null,
                         updated_at  timestamp with time zone not null
);

INSERT INTO sources (name, created_at, updated_at)
VALUES
    ('Source 1', current_timestamp, current_timestamp),
    ('Source 2', current_timestamp, current_timestamp),
    ('Source 3', current_timestamp, current_timestamp),
    ('Source 4', current_timestamp, current_timestamp),
    ('Source 5', current_timestamp, current_timestamp),
    ('Source 6', current_timestamp, current_timestamp);
