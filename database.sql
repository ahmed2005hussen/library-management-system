use book_system;

create table user_authentication(
    id       int auto_increment,
    username varchar(50)  not null,
    password varchar(100) not null,
    enabled  tinyint      default 1,
    primary key (id)
);

create table authorities(
    id        int auto_increment,
    authority varchar(50),
    user_id   int,
    primary key (id),
    foreign key (user_id) references user_authentication (id)
);

create table user_profile(
    user_id      int auto_increment,
    auth_user_id int          not null,
    phone        varchar(15)  default '',
    address      varchar(200) default '',
    primary key (user_id),
    foreign key (auth_user_id) references user_authentication (id)  
);

create table book(
    id                   int auto_increment,
    title                varchar(50) not null,
    author               varchar(50) not null,
    category             varchar(50) default 'general',
    buy_price            double      default 0.0,
    borrow_price_per_day double      default 0.0,             
    can_buy              tinyint     default 0,
    can_borrow           tinyint     default 0,
    copies               int         default 0,
    primary key (id)
);

create table borrowed_book(
    id           int auto_increment,
    user_id      int,
    book_id      int,
    borrow_price double,
    borrow_date  date,
    due_date     date,
    return_date  date,
    status       ENUM('BORROWED','RETURNED','LATE'),           
    primary key (id),
    foreign key (user_id) references user_profile (user_id),
    foreign key (book_id) references book (id)
);

create table cart(
    id      int auto_increment,
    user_id int,
    primary key (id),
    foreign key (user_id) references user_profile (user_id)
);

create table cart_item(
    id       int auto_increment,
    book_id  int,
    cart_id  int,
    type     ENUM('BUY','BORROW'),                             
    quantity int,
    primary key (id),
    foreign key (book_id) references book (id),
    foreign key (cart_id) references cart (id)
);

create table `order`(
    id          int auto_increment,
    user_id     int,
    total_price double,
    status      ENUM('PENDING','CONFIRMED','SHIPPED',
                     'DELIVERED','CANCELLED'),                  
    created_at  date,
    address     varchar(100),
    primary key (id),
    foreign key (user_id) references user_profile (user_id)
);

create table order_item(
    id       int auto_increment,
    order_id int,
    book_id  int,
    quantity int,
    price    double,
    primary key (id),
    foreign key (order_id) references `order` (id),
    foreign key (book_id) references book (id)
);