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


INSERT INTO inquiries (productRefId, customerRefId, managerRefId, source_id, comment, status, note, created_at, updated_at)
VALUES
    (1, 101, 201, 1, 'Comment 1', 'NEW', 'Note 1', current_timestamp, current_timestamp),
    (2, 102, 202, 2, 'Comment 2', 'IN_PROGRESS', 'Note 2', current_timestamp, current_timestamp),
    (3, 103, 203, 3, 'Comment 3', 'PAYMENT', 'Note 3', current_timestamp, current_timestamp),
    (4, 104, 204, 4, 'Comment 4', 'PAID', 'Note 4', current_timestamp, current_timestamp),
    (5, 105, 205, 5, 'Comment 5', 'REJECTED', 'Note 5', current_timestamp, current_timestamp),
    (6, 106, 206, 6, 'Comment 6', 'NEW', 'Note 6', current_timestamp, current_timestamp);
