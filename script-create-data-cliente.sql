CREATE database clienteHierarquia;

use clienteHierarquia;

CREATE table clientes(	
 id int primary key not null AUTO_INCREMENT,
 user_client varchar(100) not null,
 password_value varchar(10) not null,
 password_status varchar(30) not null,
 password_client varchar(80) not null
); 
