create sequence customer_seq start 1 increment 1;
create table customer (
    id numeric (20) primary key,
    name varchar (60)
);
