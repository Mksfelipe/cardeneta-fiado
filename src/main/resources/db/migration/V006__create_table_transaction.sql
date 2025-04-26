CREATE TABLE cardeneta.transaction (
    id bigserial NOT NULL,
    amount DECIMAL(10,2),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    account_id BIGINT,
    CONSTRAINT transaction_pkey PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES cardeneta.account(id)
);


ALTER TABLE cardeneta.transaction
ADD COLUMN paid CHAR(1) DEFAULT 'Y';