create table inquiries (
                         id              bigserial primary key,
                         productRefId    bigint not null,
                         customerRefId   bigint not null,
                         managerRefId    bigint not null,
                         source_id       bigint not null,
                         comment         text,
                         status          varchar(255) not null,
                         note            text,
                         created_at      timestamp with time zone not null,
                         updated_at      timestamp with time zone not null
);

