CREATE TABLE customer (
	id bigserial NOT NULL,
	"cpf" varchar(25) NOT NULL,
	"name" varchar(100) NOT NULL,
	"email" varchar(50) NOT NULL,

	CONSTRAINT uk_user_email UNIQUE ("email"),
	CONSTRAINT user_pkey PRIMARY KEY (id)
);

INSERT INTO customer (id, "cpf", "name", "email") VALUES (1, '000.000.000-01', 'Galvão Bueno', 'amoney@gmail.com');
INSERT INTO customer (id, "cpf", "name", "email") VALUES (2, '000.000.000-02', 'Batman', 'bruce@gmail.com');

ALTER SEQUENCE customer_id_seq RESTART WITH 3;