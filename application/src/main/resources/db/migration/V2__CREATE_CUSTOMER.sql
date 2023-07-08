CREATE TABLE public.customer (
	id bigserial NOT NULL,
	cpf varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT customer_entity_pkey PRIMARY KEY (id),
	CONSTRAINT uk_cpf UNIQUE (cpf)
);