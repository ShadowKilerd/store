create table product (
id int not null primary key,
name varchar(50) not null,
price decimal(10,2) not null,
unit varchar(50) not null,
total_amount int not null,
img_url varchar(500) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;