CREATE DATABASE faculty;

CREATE TABLE faculty_user (
  user_id          BIGINT       NOT NULL AUTO_INCREMENT,
  first_name       VARCHAR(50)  NOT NULL,
  last_name        VARCHAR(50)  NOT NULL,
  login            VARCHAR(50)  NOT NULL,
  password         VARCHAR(50)  NOT NULL,
  email            VARCHAR(50)  NOT NULL,
  telephone        VARCHAR(50)  NOT NULL,
  role             VARCHAR(50)  NOT NULL,
  user_blocked     BOOLEAN,
  PRIMARY KEY(user_id)
)
  ENGINE = InnoDB;

CREATE TABLE course (
  course_id        BIGINT       NOT NULL AUTO_INCREMENT,
  course_name      VARCHAR(50)  NOT NULL,
  start_time       DATETIME     NOT NULL,
  duration_weeks   INT          NOT NULL,
  student_count    INT          NOT NULL,
  user_id          BIGINT       NOT NULL,
  status           VARCHAR(50)  NOT NULL,
  price            INT          NOT NULL,
  PRIMARY KEY (course_id)
)
  ENGINE = InnoDB;

CREATE TABLE topic (
  topic_id        BIGINT        NOT NULL AUTO_INCREMENT,
  topic_name      VARCHAR(50)   NOT NULL,
  PRIMARY KEY(topic_id)
)
  ENGINE = InnoDB;

CREATE TABLE course_topic (
  course_topic_id BIGINT        NOT NULL AUTO_INCREMENT,
  course_id       BIGINT        NOT NULL,
  topic_id        BIGINT        NOT NULL,
  PRIMARY KEY(course_topic_id),
  FOREIGN KEY (course_id) REFERENCES course(course_id),
  FOREIGN KEY (topic_id) REFERENCES topic(topic_id)
)
  ENGINE = InnoDB;
  
 CREATE TABLE registration (
  registration_id BIGINT       NOT NULL AUTO_INCREMENT,
  course_id       BIGINT,
  user_id         BIGINT,
  PRIMARY KEY (registration_id),
  FOREIGN KEY (course_id) REFERENCES course(course_id),
  FOREIGN KEY (user_id) REFERENCES faculty_user(user_id)
)
  ENGINE = InnoDB;
  
 INSERT INTO faculty_user (user_id, first_name, last_name, login, password, email, telephone, role, user_blocked)
 VALUES (1, "Александр", "Олейник", "alek", "sun", "utel2010@i.ua", "0635774213", "admin", false);
 INSERT INTO faculty_user (user_id, first_name, last_name, login, password, email, telephone, role, user_blocked)
 VALUES (2,"Alena","Protas","alenadev","flover","alenka2212@ukr.net","0993042555", "user", false);
 INSERT INTO faculty_user (user_id, first_name, last_name, login, password, email, telephone, role, user_blocked)
 VALUES (3, "Александр","Михальченко","aleksandrdev","asterisk","aam@anahoret.com","0972136787","user", false);
 INSERT INTO faculty_user (user_id, first_name, last_name, login, password, email, telephone, role, user_blocked)
 VALUES (4, "Сергей","Алтухов","sergdev","serguk","sa@anahoret.com","0956781251","teacher", false);
 INSERT INTO faculty_user (user_id, first_name, last_name, login, password, email, telephone, role, user_blocked)
 VALUES (5, "Дмитрий","Колесников","dmitriy21","demon","dmitiy21@gmail.com","0967811281","user", false);
 INSERT INTO faculty_user (user_id, first_name, last_name, login, password, email, telephone, role, user_blocked)
 VALUES (6, "Георгий","Молчанов","georgiydev","hren","georgiy-epam@gmail.com","0982278178","teacher", false);
 INSERT INTO faculty_user (user_id, first_name, last_name, login, password, email, telephone, role, user_blocked)
 VALUES (7, "Алексей","Леонов","lexa","leonov","lexa-epam@gmail.com","0994622532","user", false);