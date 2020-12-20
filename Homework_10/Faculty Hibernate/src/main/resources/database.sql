-- faculty_hibernate
-- Table: users
create table faculty_users(
  user_id BINARY(16)     NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50)  NOT NULL,
  login varchar(50)      NOT NULL,
  password varchar(255)  NOT NULL,
  email varchar(50)      NOT NULL,
  telephone varchar(20)  NOT NULL,
  role varchar(30)       NOT NULL,
  user_blocked boolean   NOT NULL,
  PRIMARY KEY(user_id)
)
  ENGINE = InnoDB;

-- Table: courses
create table courses(
  course_id BINARY(16)    NOT NULL,
  course_name varchar(70) NOT NULL,
  start_time datetime     NOT NULL,
  duration_weeks int      NOT NULL,
  student_count int       NOT NULL,
  user_id bigint          NOT NULL,
  status varchar(20)      NOT NULL,
  price int               NOT NULL,
  PRIMARY KEY (course_id)
)
  ENGINE = InnoDB;

-- Table: registrations
create table registrations(
  registration_id BINARY(16)  NOT NULL,
  course_id  BINARY(16)   NOT NULL,
  user_id  BINARY(16)     NOT NULL,
  student_mark int        NOT NULL,
  approve boolean         NOT NULL,
  FOREIGN KEY (course_id) REFERENCES courses (course_id),
  FOREIGN KEY (user_id) REFERENCES faculty_users (user_id)
)
  ENGINE = InnoDB;