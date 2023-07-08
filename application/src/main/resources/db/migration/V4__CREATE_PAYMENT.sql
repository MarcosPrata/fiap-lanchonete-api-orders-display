CREATE TABLE public.payment (
	id bigserial NOT NULL,
	created_at timestamp NULL,
	payment_status varchar(255) NULL,
	total_amount float8 NOT NULL,
	order_id int8 NULL,
	CONSTRAINT payment_pkey PRIMARY KEY (id)
);


-- public.payment_entity foreign keys

ALTER TABLE public.payment ADD CONSTRAINT fk_orderid FOREIGN KEY (order_id) REFERENCES public.orders(id);