create table "order" (
    id numeric (20, 0) primary key,
    customer_id numeric (20, 0) references customer(id),
    value varchar (255)
);
