create table orders (
id varchar(50) not null primary key,
user_id varchar(50) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table order_item (
id varchar(50) not null primary key,
order_id varchar(50) not null,
amount int not null,
product_name varchar(50) not null,
product_price decimal(10,2) not null,
product_unit varchar(50) not null,
product_img_url varchar(500) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;