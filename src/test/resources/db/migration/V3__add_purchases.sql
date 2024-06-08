

CREATE TABLE IF NOT EXISTS purchases
(
    id serial,
    book_id integer,
    user_id integer,
    "time" timestamp(6) without time zone,
    CONSTRAINT purchases_pkey PRIMARY KEY (id),
    CONSTRAINT book_constraint FOREIGN KEY (book_id)
        REFERENCES public.books (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT user_constraint FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

ALTER TABLE books
ADD CONSTRAINT unique_combination
UNIQUE (name, author);