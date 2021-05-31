CREATE SEQUENCE IF NOT EXISTS tag_id_seq;

CREATE SEQUENCE IF NOT EXISTS image_id_seq;

CREATE SEQUENCE IF NOT EXISTS user_account_id_seq;

CREATE TABLE IF NOT EXISTS image
(
    id          SERIAL UNIQUE NOT NULL PRIMARY KEY,
    name        VARCHAR       NOT NULL,
    date        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    quality     VARCHAR,
    description VARCHAR,
    file        BYTEA         NOT NULL
);

CREATE TABLE IF NOT EXISTS tag
(
    id   SERIAL UNIQUE NOT NULL PRIMARY KEY,
    name VARCHAR       NOT NULL
);

CREATE TABLE IF NOT EXISTS image_tag
(
    image_id BIGINT REFERENCES image (id),
    tag_id   BIGINT REFERENCES tag (id)
);

CREATE TABLE IF NOT EXISTS user_account
(
    id       SERIAL UNIQUE NOT NULL PRIMARY KEY,
    username VARCHAR       NOT NULL,
    password VARCHAR       NOT NULL,
    role     VARCHAR       NOT NULL,
    enabled  BOOLEAN       NOT NULL
);

INSERT INTO user_account (username, password, role, enabled)
VALUES ('admin', '$2y$12$d4MJlGNUosNplKKctiX8H.mx.eQjApvMOTHlYgnRTTOLVCwzX1Q06', 'ROLE_ADMIN', true);

