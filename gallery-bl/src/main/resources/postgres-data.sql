CREATE SEQUENCE IF NOT EXISTS tag_id_seq;

CREATE SEQUENCE IF NOT EXISTS image_id_seq;

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

-- CREATE TYPE ROLE AS ENUM ('ROLE_USER','ROLE_ADMIN');

-- CREATE TABLE IF NOT EXISTS author
-- (
--     id         SERIAL       NOT NULL PRIMARY KEY,
--     name       VARCHAR(30)  NOT NULL,
--     username   VARCHAR(30)  NOT NULL UNIQUE,
--     password   VARCHAR(255) NOT NULL,
--     email      VARCHAR(100) NOT NULL UNIQUE,
--     role       ROLE         NOT NULL,
--     created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIME
-- );
--
-- INSERT INTO author (id, name, username, password, email, role)
-- VALUES (nextval(author_id_seq.nextval), 'Test', 'Testinis', '123456', 'mail@mail.com', 'ROLE_USER');
--
-- INSERT INTO image (id, name, description, file, author_id)
-- VALUES (nextval(image_id_seq.nextval), 'Waterfall', 'A beautiful waterfall scenery', pg_read_file('C:/gallery/waterfall.jpg')::bytea, 1);