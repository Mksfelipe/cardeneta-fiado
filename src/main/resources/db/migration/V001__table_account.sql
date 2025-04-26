-- cardeneta.account definition

-- Drop table

-- DROP TABLE cardeneta.account;

CREATE TABLE cardeneta.account (
    id bigserial NOT NULL,
    balance DECIMAL(10,2) DEFAULT 0,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT account_pkey PRIMARY KEY (id)
);