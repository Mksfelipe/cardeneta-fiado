-- cardeneta.users definition

-- Drop table

-- DROP TABLE cardeneta.users;

CREATE TABLE cardeneta.users (
    id bigserial NOT NULL,
    first_name varchar(20) NULL,
    last_name varchar(20) NULL,
    email varchar(50) NULL,
    "password" varchar(120) NULL,
    cpf varchar(255) NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    account_id bigint NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_cpf_key UNIQUE (cpf),
    FOREIGN KEY (account_id) REFERENCES cardeneta.account(id)
);



