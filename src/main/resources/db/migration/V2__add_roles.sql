ALTER TABLE users
ADD COLUMN password character varying(255) NOT NULL,
ADD COLUMN role character varying(255) NOT NULL,
ADD CONSTRAINT users_role_check CHECK (role::text = ANY (ARRAY['USER'::character varying, 'ADMIN'::character varying]::text[]))

