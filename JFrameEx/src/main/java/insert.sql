create TABLE users(
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  surname VARCHAR,
  univGroup INT,
  isKazan BOOLEAN
)

INSERT INTO users(name, surname, univgroup, iskazan) VALUES ('Cаша', 'Доброквашина', 502, true);