create table inquiries (
                         id              bigserial primary key,
                         source_id       bigint not null,
                         comment         text,
                         status          varchar(255) not null,
                         note            text,
                         created_at      timestamp with time zone not null,
                         updated_at      timestamp with time zone not null
);

