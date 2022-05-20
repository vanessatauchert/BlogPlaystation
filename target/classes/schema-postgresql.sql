CREATE TABLE IF NOT EXISTS form(
    id serial PRIMARY KEY,
    name varchar(50),
    email varchar(50),
    msg varchar(250)
);