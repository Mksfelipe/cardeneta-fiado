ALTER SEQUENCE cardeneta.roles_id_seq RESTART WITH 1;
ALTER SEQUENCE cardeneta.users_id_seq RESTART WITH 1;
ALTER SEQUENCE cardeneta.account_id_seq RESTART WITH 1;
ALTER SEQUENCE cardeneta.transaction_id_seq RESTART WITH 1;
ALTER SEQUENCE cardeneta.payment_id_seq RESTART WITH 1;

DELETE FROM cardeneta.user_roles;
DELETE FROM cardeneta.users;
DELETE FROM cardeneta.roles;
DELETE FROM cardeneta.transaction;
DELETE FROM cardeneta.payment;
DELETE FROM cardeneta.account;

INSERT INTO cardeneta.account (id, balance, created, updated ) VALUES(nextval('cardeneta.account_id_seq'::regclass), 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO cardeneta.users (id, first_name, last_name,	email,	"password",	cpf, created, updated,	account_id,	contact) VALUES (nextval('cardeneta.users_id_seq'::regclass),'FELIPE','SOUSA','sousafelipe123@gmail.com','$2a$10$srioWy1sCAQbr7S9mOX2HOnM91PurhxneyX15rJMKbjOUoPkYMouy','077.821.693-48',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,1,null::character varying);

INSERT INTO cardeneta.roles(name) VALUES('ROLE_USER');
INSERT INTO cardeneta.roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO cardeneta.roles(name) VALUES('ROLE_ADMIN');

INSERT INTO cardeneta.user_roles (role_id, user_id) VALUES(3, 1);
