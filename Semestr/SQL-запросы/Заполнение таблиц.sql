INSERT INTO universities(univ_name, country, city, address, about)
    VALUES ('КФУ', 'Россия', 'Казань', 'Кремлевская 35', 'Казанский Федеральный Университет');
INSERT INTO universities(univ_name, country, city, address, about)
VALUES ('КАИ', 'Россия', 'Казань', '---', 'Казанский Авиационный Институт');
INSERT INTO universities(univ_name, country, city, address, about)
VALUES ('МГУ', 'Россия', 'Москва', 'Ленинские горы', 'Московский Государственный Университет');

INSERT INTO subjects VALUES (1, 'Русский язык');
INSERT INTO subjects VALUES (2, 'Математика');
INSERT INTO subjects VALUES (3, 'Информатика');
INSERT INTO subjects VALUES (4, 'Английский язык');
INSERT INTO subjects VALUES (5, 'Физика');
INSERT INTO subjects VALUES (6, 'Химия');
INSERT INTO subjects VALUES (7, 'Литература');
INSERT INTO subjects VALUES (8, 'История');
INSERT INTO subjects VALUES (9, 'Обществознание');
INSERT INTO subjects VALUES (10, 'География');

INSERT INTO specialities(spec_name, subject1, subject2, subject3)
    VALUES ('Прикладная информатика',2,3,1);
INSERT INTO specialities(spec_name, subject1, subject2, subject3, about)
VALUES ('Прикладная математика',1,2,5,'физический факультет');
INSERT INTO specialities(spec_name, subject1, subject2, subject3, subject4, about)
VALUES ('Прикладная философия?',1,8,9,7,'Сюда наверное невозможно поступить');

INSERT INTO  achivements(ach_sub, ach_name)
    VALUES (1, 'Золотая медаль');
INSERT INTO  achivements(ach_sub, ach_name)
VALUES (3, 'Олимпиада по информатике');

INSERT INTO points VALUES (1,1,250,150,130,130);
INSERT INTO points VALUES (1,2,270,140,130,130);
INSERT INTO points VALUES (2,2,255,160,125,130);
INSERT INTO points VALUES (3,3,330,200,180,180);

INSERT INTO extrapoints VALUES (1,1,3);

INSERT INTO costs VALUES (1,1,105000,100000,10000);
INSERT INTO costs VALUES (1,2,90000,100000,10000);
INSERT INTO costs VALUES (2,2,100000,100000,10000);
INSERT INTO costs VALUES (3,3,99000,100000,10000);




