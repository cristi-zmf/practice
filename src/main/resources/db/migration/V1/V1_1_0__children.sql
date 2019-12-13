create table c_order (
    id decimal (20) primary key,
    value varchar (255),
    customer_id decimal(20)
);

create table customer_orders (
    customer_id decimal (20),
    orders_id numeric (20, 0)
)
