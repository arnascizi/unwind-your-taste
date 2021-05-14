DROP TABLE IF EXISTS image;

CREATE TABLE image
(
    id          IDENTITY PRIMARY KEY,
    name        VARCHAR(60)  NOT NULL,
    description VARCHAR(255),
    url         VARCHAR(255) NOT NULL
);

INSERT INTO image (name, url, description)
VALUES ('One', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
        'description 1');

INSERT INTO image (name, url, description)
VALUES ('Two', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
        'description 2');

INSERT INTO image (name, url, description)
VALUES ('Three', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
        'description 3');

INSERT INTO image (name, url, description)
VALUES ('Four', 'https://i.pinimg.com/564x/17/78/c1/1778c168d724dc084002033f5b8b586c.jpg',
        'description 4');
