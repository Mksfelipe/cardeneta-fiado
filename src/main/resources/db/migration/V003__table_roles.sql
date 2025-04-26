-- cardeneta.roles definition

-- Drop table

-- DROP TABLE cardeneta.roles;

CREATE TABLE cardeneta.roles (
    id serial4 NOT NULL,
    "name" varchar(20) NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id),
    CONSTRAINT roles_name_check CHECK (length("name") <= 20)
);