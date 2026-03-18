DROP DATABASE IF EXISTS book_system;
CREATE DATABASE book_system;
USE book_system;

CREATE TABLE user_authentication
(
    id       INT AUTO_INCREMENT,
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  TINYINT DEFAULT 1,
    PRIMARY KEY (id)
);

CREATE TABLE authorities
(
    id        INT AUTO_INCREMENT,
    authority VARCHAR(50),
    user_id   INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_authentication (id)
);

CREATE TABLE user_profile
(
    user_id      INT AUTO_INCREMENT,
    auth_user_id INT         NOT NULL,
    first_name   VARCHAR(50),
    last_name    VARCHAR(50),
    phone        VARCHAR(15)  DEFAULT '',
    address      VARCHAR(200) DEFAULT '',
    PRIMARY KEY (user_id),
    FOREIGN KEY (auth_user_id) REFERENCES user_authentication (id)
);

CREATE TABLE book
(
    id                   INT AUTO_INCREMENT,
    title                VARCHAR(50)   NOT NULL,
    author               VARCHAR(50)   NOT NULL,
    category             VARCHAR(50)   DEFAULT 'general',
    buy_price            DECIMAL(10,2) DEFAULT 0.00,
    borrow_price_per_day DECIMAL(10,2) DEFAULT 0.00,
    can_buy              BOOLEAN       DEFAULT FALSE,
    can_borrow           BOOLEAN       DEFAULT FALSE,
    copies               INT UNSIGNED  DEFAULT 0,
    `year`               YEAR          NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE borrowed_book
(
    id           INT AUTO_INCREMENT,
    user_id      INT,
    book_id      INT,
    borrow_price DECIMAL(10,2),
    borrow_date  DATE,
    due_date     DATE,
    return_date  DATE,
    status       ENUM ('BORROWED','RETURNED','LATE'),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_profile (user_id),
    FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE cart
(
    id      INT AUTO_INCREMENT,
    user_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_profile (user_id)
);

CREATE TABLE cart_item
(
    id       INT AUTO_INCREMENT,
    book_id  INT,
    cart_id  INT,
    type     ENUM ('BUY','BORROW'),
    quantity INT,
    PRIMARY KEY (id),
    FOREIGN KEY (book_id) REFERENCES book (id),
    FOREIGN KEY (cart_id) REFERENCES cart (id)
);

CREATE TABLE `order`
(
    id          INT AUTO_INCREMENT,
    user_id     INT,
    total_price DECIMAL(10,2),
    status      ENUM ('PENDING','CONFIRMED','SHIPPED','DELIVERED','CANCELLED'),
    created_at  DATE,
    updated_at  DATE,
    address     VARCHAR(100),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_profile (user_id)
);

CREATE TABLE order_item
(
    id       INT AUTO_INCREMENT,
    order_id INT,
    book_id  INT,
    quantity INT,
    price    DECIMAL(10,2),
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES `order` (id),
    FOREIGN KEY (book_id) REFERENCES book (id)
);