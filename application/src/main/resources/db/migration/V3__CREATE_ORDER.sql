CREATE TABLE public.orders (
	id bigserial NOT NULL,
	created_at timestamp NULL,
	notes varchar(255) NULL,
	status int4 NULL,
	updated_at timestamp NULL,
	customer_id int8 NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id)
);


-- public.order_entity foreign keys

ALTER TABLE public.orders ADD CONSTRAINT fk_customerid FOREIGN KEY (customer_id) REFERENCES public.customer(id);


CREATE TABLE public.orders_products (
	order_id int8 NOT NULL,
	product_id int8 NOT NULL
);


-- public.orders_products foreign keys

ALTER TABLE public.orders_products ADD CONSTRAINT fk_orderid FOREIGN KEY (order_id) REFERENCES public.orders(id);
ALTER TABLE public.orders_products ADD CONSTRAINT fk_productid FOREIGN KEY (product_id) REFERENCES public.product(id);