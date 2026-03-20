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
    user_id   INT NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY (user_id , authority),
    FOREIGN KEY (user_id) REFERENCES user_authentication (id) ON DELETE CASCADE
);

CREATE TABLE user_profile
(
    user_id INT,
    first_name   VARCHAR(50),
    last_name    VARCHAR(50),
    phone        VARCHAR(15)  DEFAULT '',
    address      VARCHAR(200) DEFAULT '',
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES user_authentication (id) ON DELETE CASCADE
);

CREATE TABLE book
(
    id                   INT AUTO_INCREMENT,
    isbn                 VARCHAR(13)   NOT NULL UNIQUE,
    title                VARCHAR(100)  NOT NULL,
    author               VARCHAR(100)  NOT NULL,
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
    user_id      INT NOT NULL,
    book_id      INT NOT NULL,
    borrow_price DECIMAL(10,2),
    borrow_date  DATE,
    due_date     DATE,
    return_date  DATE,
    status       ENUM ('BORROWED','RETURNED','LATE') DEFAULT 'BORROWED',
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_profile (user_id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE
);

CREATE TABLE cart
(
    id      INT AUTO_INCREMENT,
    user_id INT UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_profile (user_id) ON DELETE CASCADE
);

CREATE TABLE cart_item
(
    id       INT AUTO_INCREMENT,
    book_id  INT,
    cart_id  INT,
    type     ENUM ('BUY','BORROW'),
    quantity INT,
    PRIMARY KEY (id),
    UNIQUE (cart_id, book_id, type),
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE,
    FOREIGN KEY (cart_id) REFERENCES cart (id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    id          INT AUTO_INCREMENT,
    user_id     INT,
    total_price DECIMAL(10,2),
    status      ENUM ('PENDING','CONFIRMED','SHIPPED','DELIVERED','CANCELLED'),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    address     VARCHAR(100),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user_profile (user_id) ON DELETE CASCADE
);

CREATE TABLE order_item
(
    id       INT AUTO_INCREMENT,
    order_id INT,
    book_id  INT,
    quantity INT,
    price    DECIMAL(10,2),
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE
);

INSERT INTO user_authentication (username, password, enabled) 
VALUES ('ahmed', '$2a$10$DbzGzXexIw6CcTxCpcy5heQIvWn.CnKIFikv1HiOMqxH7539BpJdC', 1);

INSERT INTO authorities (user_id, authority) 
VALUES (1, 'ROLE_ADMIN');