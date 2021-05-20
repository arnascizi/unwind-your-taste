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

-- INSERT INTO image (name, url, description)
-- VALUES ('One', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
--         'description 1'),
--        ('Two', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
--         'description 2'),
--        ('Three', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
--         'description 3'),
--        ('Four', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
--         'description 4'),
--        ('Five', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
--         'description 5'),
--        ('Six', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
--         'description 6'),
--        ('Seven', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
--         'description 7'),
--        ('Eight', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
--         'description 8');
