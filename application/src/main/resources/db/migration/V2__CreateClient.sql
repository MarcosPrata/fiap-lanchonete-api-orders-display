CREATE TABLE client (
	id bigserial NOT NULL,
	"name" varchar(100) NOT NULL,
	"email" varchar(50) NOT NULL,
	"cpf" varchar(25) NOT NULL,

	CONSTRAINT uk_user_email UNIQUE ("email"),
	CONSTRAINT user_pkey PRIMARY KEY (id)
);