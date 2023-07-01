CREATE TABLE product (
	id bigserial NOT NULL,
    name varchar(100) NOT NULL,
	price float NOT NULL,
	category varchar(25) NOT NULL,

	CONSTRAINT product_pkey PRIMARY KEY (id)
);