ALTER TABLE cardeneta.transaction
ADD COLUMN payment_id BIGINT,
ADD CONSTRAINT fk_payment_id
FOREIGN KEY (payment_id) REFERENCES payment(id);