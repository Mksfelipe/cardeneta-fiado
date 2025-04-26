CREATE TABLE cardeneta.payment (
    id bigserial PRIMARY KEY,
    amount_paid DECIMAL(10,2),
    change_amount DECIMAL(10,2),
    account_id BIGINT,
    payment_date DATE,
    FOREIGN KEY (account_id) REFERENCES account(id)
);