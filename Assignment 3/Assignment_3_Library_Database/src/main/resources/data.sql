INSERT INTO users (role, userName, password) VALUES
    ('CLIENT', 'test', '$2a$12$VztsG4k0lMaY6FmwQpkWUe3nk8DObvDiYqDODfgnL246Ta/PHKbVm'),
    ('CLIENT', 'test1', '$2a$12$ExuFnpVd3QLxfFS.3uvUCulVT4.VYWYc0.WfdkaXVlRSOUt93nKIm');

-- Add one administrator
INSERT INTO users (role, userName, password) VALUES
    ('ADMIN', 'root', '$2a$04$7naxHAxFnD9jJ795/lTynONDOyql23XumSks6n0lgaxxBCO4omkZm');

insert into books (title, author)
values ('The 7 Habits of Highly Effective People', 'Stephen R. Covey');

insert into books (title, author)
values ('The Martian', 'Andy Weir');

insert into reviews (text, bookId)
values ('An older book, but still a very good read for principle—centered leadership.', 1);

insert into reviews (text, bookId)
values ('A great science fiction book about an astronaut stranded on Mars', 2);