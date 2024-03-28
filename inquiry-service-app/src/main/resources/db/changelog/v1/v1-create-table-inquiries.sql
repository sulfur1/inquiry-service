create table inquiries (
                         id              bigserial primary key,
                         source_id       bigint not null,
                         customer_ref_id  bigint not null,
                         product_ref_id  bigint not null,
                         manager_ref_id  bigint not null,
                         comment         text,
                         status          varchar(255) not null,
                         note            text,
                         created_at      timestamp with time zone not null DEFAULT NOW(),
                         updated_at      timestamp with time zone not null DEFAULT NOW(),
                         CONSTRAINT      fk_source_id FOREIGN KEY(source_id)
                         REFERENCES      sources(id)
);

CREATE INDEX IF NOT EXISTS source_id_idx ON inquiries (source_id);
