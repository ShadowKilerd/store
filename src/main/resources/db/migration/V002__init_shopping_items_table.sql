create table shopping_item (
id varchar(50) not null primary key,
user_id varchar(50) not null,
product_id varchar(50) not null,
amount int not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;