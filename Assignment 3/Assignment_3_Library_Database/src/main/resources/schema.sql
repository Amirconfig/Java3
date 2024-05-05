create table books (
    id LONG NOT NULL Primary Key AUTO_INCREMENT,
    title VARCHAR(128) NOT NULL,
    author VARCHAR(128) NOT NULL
);

create table reviews (
    id LONG NOT NULL Primary Key AUTO_INCREMENT,
    bookId LONG NOT NULL,
    text VARCHAR(1024) NOT NULL UNIQUE
);

create table users (
    id LONG NOT NULL Primary Key AUTO_INCREMENT,
    role VARCHAR(10) NOT NULL,
    userName VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);


alter table reviews
    add constraint book_review_fk foreign key (bookId)
        references books (id);

