CREATE TABLE "order" (
	id bigserial NOT NULL,
	client_id bigint NOT NULL,

	CONSTRAINT fk_order_client FOREIGN KEY ("client_id") REFERENCES client(id),
	CONSTRAINT order_pkey PRIMARY KEY (id)
);


CREATE TABLE orderItem (
	id bigserial NOT NULL,
	ammount integer NOT NULL,
	product_id bigint NOT NULL,
	note varchar(50) NOT NULL,
	order_id bigint NOT NULL,

	CONSTRAINT fk_orderitem_item FOREIGN KEY ("product_id") REFERENCES product(id),
	CONSTRAINT fk_orderitem_order FOREIGN KEY ("order_id") REFERENCES "order"(id),
	CONSTRAINT orderitem_pkey PRIMARY KEY (id)
);