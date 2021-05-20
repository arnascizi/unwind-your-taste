CREATE SEQUENCE IF NOT EXISTS tag_id_seq;

CREATE SEQUENCE IF NOT EXISTS image_id_seq;

CREATE SEQUENCE IF NOT EXISTS user_account_id_seq;

CREATE TYPE ROLE AS ENUM ('ROLE_USER', 'ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS image
(
    id          SERIAL UNIQUE NOT NULL PRIMARY KEY,
    name        VARCHAR(60)   NOT NULL,
    date        TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    quality     VARCHAR(60),
    description VARCHAR(100),
    file        BYTEA         NOT NULL
);

CREATE TABLE IF NOT EXISTS tag
(
    id   SERIAL UNIQUE NOT NULL PRIMARY KEY,
    name VARCHAR(20)   NOT NULL
);

CREATE TABLE IF NOT EXISTS image_tag
(
    image_id BIGINT REFERENCES image (id),
    tag_id   BIGINT REFERENCES tag (id)
);

CREATE TABLE IF NOT EXISTS user_account
(
    id       SERIAL UNIQUE NOT NULL PRIMARY KEY,
    username VARCHAR(50)   NOT NULL,
    password VARCHAR(255)  NOT NULL,
    role     ROLE          NOT NULL,
    enabled  boolean       not null
);

INSERT INTO user_account (username, password, role, enabled)
VALUES ('admin', '$2y$12$d4MJlGNUosNplKKctiX8H.mx.eQjApvMOTHlYgnRTTOLVCwzX1Q06 ', 'ROLE_ADMIN', true);