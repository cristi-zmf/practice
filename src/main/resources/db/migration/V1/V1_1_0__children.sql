create table "order" (
    id decimal (20) primary key,
    value varchar (255)
);

create table "customer_orders" (
    customer_id decimal (20),
    orders_id numeric (20, 0)
)
