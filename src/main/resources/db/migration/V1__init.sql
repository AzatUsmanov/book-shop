CREATE TABLE IF NOT EXISTS users
(
    id serial,
    username character varying(50) NOT NULL,
    money_in_account numeric(38,2) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_username_key UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS books
(
    id serial,
    author character varying(50) NOT NULL,
    name character varying(50) NOT NULL,
    description character varying(250),
    price numeric(38,2) NOT NULL,
    CONSTRAINT books_pkey PRIMARY KEY (id)
);