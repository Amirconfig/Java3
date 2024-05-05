CREATE TABLE students(
	id LONG NOT NULL PRIMARY KEY AUTO_INCREMENT,
	number LONG NOT NULL UNIQUE,
	name VARCHAR(120) NOT NULL
);

CREATE TABLE courses(
	id LONG NOT NULL PRIMARY KEY AUTO_INCREMENT,
	studentId LONG NOT NULL,
	name VARCHAR(120) NOT NULL,
	grade LONG NOT NULL
);

ALTER TABLE courses
ADD CONSTRAINT student_course_fk FOREIGN KEY (studentId)
REFERENCES students(id);

INSERT INTO students(number, name) VALUES
(1234, 'Charlie Brown'),
(4321, 'Lucy');

INSERT INTO courses(name, grade, studentId) VALUES
('Java 1', 83, 1),
('Java 3', 92, 2),
('Database', 78, 2);
