CREATE TABLE image
(
    id          IDENTITY PRIMARY KEY NOT NULL,
    name        VARCHAR(60)          NOT NULL,
    date        DATETIME,
    quality     VARCHAR(60),
    description VARCHAR(100)
);

CREATE TABLE tag
(
    id   IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(20)          NOT NULL
);

CREATE TABLE image_tag
(
    image_id BIGINT REFERENCES image (id),
    tag_id   BIGINT REFERENCES tag (id)
);
