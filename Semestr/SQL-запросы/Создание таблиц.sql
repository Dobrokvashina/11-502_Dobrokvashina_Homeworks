DROP VIEW IF EXISTS table_of_res_points;
DROP TRIGGER IF EXISTS on_add ON user_points;
DROP TRIGGER IF EXISTS on_delete ON user_points;
DROP FUNCTION IF EXISTS on_delete_row();
DROP FUNCTION IF EXISTS  add_to_table();
DROP TABLE IF EXISTS user_points_in_spec, user_achive, user_points, users, costs, ExtraPoints, points, achivements, specialities, subjects, universities;

CREATE TABLE universities (
  id        SERIAL PRIMARY KEY,
  univ_name VARCHAR     NOT NULL,
  country   VARCHAR(20) NOT NULL,
  city      VARCHAR(20) NOT NULL,
  address   TEXT,
  about     TEXT
);

CREATE TABLE subjects (
  id       INT PRIMARY KEY,
  sub_name VARCHAR(20)
);


CREATE TABLE specialities (
  id        SERIAL PRIMARY KEY,
  spec_name VARCHAR NOT NULL,
  subject1  INT     NOT NULL,
  subject2  INT     NOT NULL,
  subject3  INT     NOT NULL,
  subject4  INT,
  subject5  INT,
  about     TEXT,
  FOREIGN KEY (subject1) REFERENCES subjects (id),
  FOREIGN KEY (subject2) REFERENCES subjects (id),
  FOREIGN KEY (subject3) REFERENCES subjects (id),
  FOREIGN KEY (subject4) REFERENCES subjects (id),
  FOREIGN KEY (subject5) REFERENCES subjects (id)
);

CREATE TABLE achivements (
  id       SERIAL PRIMARY KEY,
  ach_sub  INT REFERENCES subjects (id),
  ach_name TEXT
);


CREATE TABLE points (
  univ_id             INT,
  spec_id             INT,
  budjet              INT CHECK (budjet > 0),
  day_contract        INT CHECK (day_contract > 0),
  evening_form        INT CHECK (evening_form > 0),
  correspondence_form INT CHECK (correspondence_form > 0),
  FOREIGN KEY (univ_id) REFERENCES universities (id) ON DELETE CASCADE,
  FOREIGN KEY (spec_id) REFERENCES specialities (id) ON DELETE CASCADE,
  PRIMARY KEY (univ_id, spec_id)
);


CREATE TABLE ExtraPoints (
  univ_id INT REFERENCES universities (id),
  ach_id  INT REFERENCES achivements (id),
  points  INT CHECK (points > 0),
  PRIMARY KEY (univ_id, ach_id)
);

CREATE TABLE costs (
  univ_id             INT REFERENCES universities (id) ON DELETE CASCADE,
  spec_id             INT REFERENCES specialities (id) ON DELETE CASCADE,
  day_contract        NUMERIC NOT NULL,
  evening_form        NUMERIC,
  correspondence_form NUMERIC,
  PRIMARY KEY (univ_id, spec_id)
);

CREATE TABLE users (
  id           SERIAL PRIMARY KEY,
  user_name    VARCHAR(15),
  user_surname VARCHAR(20),
  country      VARCHAR(20),
  city         VARCHAR(20),
  user_login   VARCHAR(20) NOT NULL,
  user_pasword VARCHAR(20) NOT NULL
);

CREATE TABLE user_points (
  user_id    INT REFERENCES users (id) ON DELETE CASCADE,
  subject_id INT REFERENCES subjects (id),
  points     INT CHECK (points <= 100),
  CONSTRAINT points_right CHECK (points >= 0)
);

CREATE TABLE user_achive (
  user_id INT REFERENCES users (id) ON DELETE CASCADE,
  ach_id  INT REFERENCES achivements (id)
);

CREATE TABLE user_points_in_spec (
  spec_id INT REFERENCES specialities (id) ON DELETE CASCADE,
  user_id INT REFERENCES users (id) ON DELETE CASCADE,
  points  INT
);


CREATE VIEW table_of_res_points AS
  SELECT
    UP1.user_id,
    UP1.spec_name,
    UP1.points
  FROM (SELECT
          S.spec_name,
          UP.user_id,
          UP.points
        FROM user_points_in_spec AS UP INNER JOIN
          (SELECT
             id,
             spec_name
           FROM specialities) AS S ON (UP.spec_id = S.id)) AS UP1;

CREATE OR REPLACE FUNCTION add_to_table()
  RETURNS TRIGGER AS $$
DECLARE
  rec RECORD;
  res INT;
BEGIN
  FOR rec IN SELECT
               id,
               subject1,
               subject2,
               subject3,
               subject4,
               subject5
             FROM specialities
             WHERE (subject1 = new.subject_id OR subject2 = new.subject_id OR subject3 = new.subject_id OR
                    subject4 = new.subject_id OR subject5 = new.subject_id)
  LOOP
    IF EXISTS(SELECT *
              FROM user_points
              WHERE (user_id = new.user_id AND (subject_id = rec.subject1 OR rec.subject1 = new.subject_id))) AND
       EXISTS(SELECT *
              FROM user_points
              WHERE (user_id = new.user_id AND (subject_id = rec.subject2 OR rec.subject2 = new.subject_id)) AND
                    EXISTS(SELECT *
                           FROM user_points
                           WHERE
                             (user_id = new.user_id AND (subject_id = rec.subject3 OR rec.subject3 = new.subject_id)))
                    AND
                    (rec.subject4 ISNULL OR EXISTS(SELECT *
                                                   FROM user_points
                                                   WHERE (user_id = new.user_id AND (subject_id = rec.subject4 OR
                                                                                     rec.subject4 = new.subject_id))))
                    AND
                    (rec.subject5 ISNULL OR EXISTS(SELECT *
                                                   FROM user_points
                                                   WHERE (user_id = new.user_id AND (subject_id = rec.subject5 OR
                                                                                     rec.subject5 = new.subject_id)))))
    THEN
      SELECT sum(points)
      INTO res
      FROM user_points
      WHERE (user_id = new.user_id AND (
        subject_id = rec.subject1 OR subject_id = rec.subject2 OR subject_id = rec.subject3 OR subject_id = rec.subject4
        OR subject_id = rec.subject5));
      INSERT INTO user_points_in_spec VALUES (rec.id, new.user_id, res + new.points);
    END IF;
  END LOOP;
  RETURN new;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION on_delete_row()
  RETURNS TRIGGER AS $$
DECLARE
  rec RECORD;
BEGIN
  FOR rec IN SELECT id FROM specialities where (subject1 = old.subject_id OR subject2 = old.subject_id OR subject3 = old.subject_id OR subject4 = old.subject_id OR subject5 = old.subject_id)
    LOOP
  DELETE FROM user_points_in_spec
  WHERE (user_id = old.user_id AND spec_id = rec.id);
    END LOOP;
  RETURN old;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER on_add BEFORE INSERT ON user_points FOR EACH
ROW EXECUTE PROCEDURE add_to_table();

CREATE TRIGGER on_delete BEFORE DELETE ON user_points FOR EACH
ROW EXECUTE PROCEDURE on_delete_row();




