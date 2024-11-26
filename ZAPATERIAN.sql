/*CREACION DE TABLAS*/

/*EMPLEADOS*/
create table empleados(
IdEMPLEADOS tinyint,
Nombres varchar(40) not null,
Contraseña smallint not null,
HorarioEntrada time not null,
Teléfono char(10) not null,
HorarioSalida time not null,
Apellidos varchar(40) not null,
Correo varchar(50) not null,
Puesto varchar(10) not null,
primary key(IdEMPLEADOS)
);

/*PRODUCTOS*/
create table productos(
IdPRODUCTOS smallint,
STOCK smallint not null,
PRECIO float not null,
COSTO float not null,
NOMBREPRODUCTO varchar(50) not null,
TALLA float not null,
primary key(IdPRODUCTOS)
);

/*VENTA*/
create table venta(
FOLIOV smallint, 
IdEMPLEADOS tinyint,
NUMV smallint,
FECHA date not null,
primary key (FOLIOV),
foreign key(IdEMPLEADOS) references empleados(IdEMPLEADOS) ON UPDATE CASCADE ON DELETE CASCADE
);

/*DETALLE DE VENTA*/
create table detalleDeVentas(
NUMV smallint primary key,
IdPRODUCTOS smallint,
TOTALDEVENTA float(10,2) not null,
PRECIO float (10,2) not null,
COSTO float (10,2) not null,
METODODEPAGO char (1) not null,
CANTIDAD smallint,
FOLIOV smallint,
foreign key(IdPRODUCTOS) references productos(IdPRODUCTOS) ON UPDATE CASCADE ON DELETE CASCADE,
foreign key(FOLIOV) references venta(FOLIOV) ON UPDATE CASCADE ON DELETE CASCADE
);

/*PROVEEDORES*/
create table proveedores(
IdPROVEEDORES tinyint not null,
NOMBRE varchar(40) not null,
DIRECCION varchar(100) not null,
CORREO varchar(30) not null,
TELEFONO char(14) not null,
primary key(IdPROVEEDORES)
);

/*COMPRA*/
create table compra( 
FOLIOC smallint primary key, 
IdEMPLEADOS tinyint,
IdPROVEEDORES tinyint,
FECHA date not null,
foreign key(IdPROVEEDORES) references proveedores(IdPROVEEDORES) ON UPDATE CASCADE ON DELETE CASCADE,
foreign key(IdEMPLEADOS) references empleados(IdEMPLEADOS) ON UPDATE CASCADE ON DELETE CASCADE
);

/*DETALLE DE COMPRA*/
create table detalleDeCompras(
NUMC smallint primary key,
TOTALDECOMPRA float(10,2) not null,
PRECIO float (10,2) not null,
COSTO float (10,2) not null,
METODODEPAGO varchar (15) not null,
CANTIDAD smallint,
IdPRODUCTOS smallint,
FOLIOC smallint,
foreign key(IdPRODUCTOS) references productos(IdPRODUCTOS) ON UPDATE CASCADE ON DELETE CASCADE,
foreign key(FOLIOC) references compra(FOLIOC) ON UPDATE CASCADE ON DELETE CASCADE
);

/*INSERTAR DATOS EMPLEADOS*/
insert into empleados values(1,'Juan José', 2020, '09:30:00', '2298385832', '18:30:00', 'Sanchez Hernández', 'JuanJ20@gmail.com', 'Bodeguero');
insert into empleados values(2,'Felipe', 1010, '09:30:00', '2290801022', '18:30:00', 'Gutierrez Santos', 'Felipe10@gmail.com', 'Gerente');
insert into empleados values(3,'Leilani', 2525, '09:30:00', '2294229883', '18:30:00', 'Reyes Mendoza', 'Leilani25@gmail.com', 'Cajera');
insert into empleados values(4,'Luz Maria', 1001, '09:30:00', '2285956331', '18:30:00', 'Lopez Sosa', 'LuzM10@gmail.com', 'Vendedora');
insert into empleados values(5,'Roberto', 1323, '09:30:00', '2285956373', '18:30:00', 'Mendez Aquino', 'RobMEN@gmail.com', 'Bodeguero');
insert into empleados values(6,'Andrea', 5155, '09:30:00', '2297634120', '18:30:00', 'Cabrera Iglesias', 'Andrea51@gmail.com', 'Vendedora');
insert into empleados values(7,'Armando', 2898, '09:30:00', '2291456325', '18:30:00', 'Hernández Morales', 'Armando28@gmail.com', 'Cajero');
select * from empleados;

/*INSERTAR DATOS PROVEEDORES*/
insert into proveedores values(1,'Germán Guzmán Mendez','Col. Centro, calle Díaz Mirón, num 987', 'german10@gmail.com', '2293456786');

/*INSERTAR DATOS PRODUCTOS*/
insert into productos values(1,5,500.00,300.00,'BotasBajasNegras',3);
insert into productos values(2,5,500.00,300.00,'BotasBajasNegras',4);
insert into productos values(3,5,500.00,300.00,'BotasBajasNegras',5);
insert into productos values(4,5,450.00,250.00,'BotasBajasBeige',3);
insert into productos values(5,5,450.00,250.00,'BotasBajasBeige',4);
insert into productos values(6,5,450.00,250.00,'BotasBajasBeige',5);
insert into productos values(7,5,500.00,300.00,'BotasBajasBlancas',3);
insert into productos values(8,5,500.00,300.00,'BotasBajasBlancas',4);
insert into productos values(9,5,500.00,300.00,'BotasBajasBlancas',5);

/*INSERTAR REGISTROS DE VENTA*/
insert into venta values (01, 3, 01, '2024-10-16');
insert into venta values (02, 3, 02, '2024-10-16');
insert into venta values (03, 7, 03, '2024-10-16');
insert into venta values (04, 3, 04, '2024-10-16');
insert into venta values (05, 3, 01, '2024-10-16');
insert into venta values (06, 7, 02, '2024-10-16');
insert into venta values (07, 3, 03, '2024-10-16');
insert into venta values (08, 7, 04, '2024-10-16');
insert into venta values (09, 7, 01, '2024-10-16');
insert into venta values (10, 3, 02, '2024-10-16');
insert into venta values (11, 3, 03, '2024-10-16');
insert into venta values (12, 7, 04, '2024-10-16');

/*INSERTAR REGISTROS DE DETALLE DE VENTA*/
insert into detalleDeVentas values (1, 1, 1000, 500, 300, 'E', 2, 1);
insert into detalleDeVentas values (2, 3, 500, 500, 300, 'E', 1, 2);
insert into detalleDeVentas values (3, 5, 1800, 450, 250, 'T', 4, 3);
insert into detalleDeVentas values (4, 2, 500, 500, 300, 'E', 1, 4);
insert into detalleDeVentas values (5, 9, 2000, 500, 300, 'E', 4, 5);
insert into detalleDeVentas values (6, 10, 800, 400, 200, 'T', 2, 6);
insert into detalleDeVentas values (7, 11, 1200, 400, 200, 'E', 3, 7);
insert into detalleDeVentas values (8, 8, 1000, 500, 300, 'T', 2, 8);
insert into detalleDeVentas values (9, 7, 1500, 500, 300, 'T', 3, 9);
