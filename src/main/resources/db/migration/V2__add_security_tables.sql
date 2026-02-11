CREATE TABLE users (
        username VARCHAR(50) NOT NULL PRIMARY KEY,
        password VARCHAR(100) NOT NULL,
        enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE authorities (
        username VARCHAR(50) NOT NULL,
      authority VARCHAR(50) NOT NULL,
        CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

-- Додаємо користувача 'user' з паролем 'jdbcDefault'
-- {noop} означає, що пароль не захешований (для навчальних цілей)
INSERT INTO users (username, password, enabled)
VALUES ('user', '{noop}jdbcDefault', true);

INSERT INTO authorities (username, authority)
VALUES ('user', 'ROLE_USER');