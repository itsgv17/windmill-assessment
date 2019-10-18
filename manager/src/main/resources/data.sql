CREATE TABLE IF NOT EXISTS windmill (
  serial_number VARCHAR(16) NOT NULL,
  name VARCHAR(100),
  address VARCHAR(200), 
  latitude NUMERIC(9,6) NOT NULL,
  longitude NUMERIC(9,6) NOT NULL,
  PRIMARY KEY(serial_number),
  UNIQUE(latitude,longitude)
  );


CREATE TABLE IF NOT EXISTS windmill_metric(
serial_number VARCHAR(16) NOT NULL,
date_time TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
power FLOAT,
PRIMARY KEY(serial_number, date_time),
FOREIGN KEY (serial_number) REFERENCES windmill(serial_number)
);


insert into windmill (serial_number, name, address, latitude, longitude) values ('1234567890qwerty', 'windmill1','chennai', 123.456789,100.123456);
insert into windmill (serial_number, name, address, latitude, longitude) values ('1234567890qwerta', 'windmill2','chennai', 100.456789,150.123456);
insert into windmill (serial_number, name, address, latitude, longitude) values ('1234567890qwertb', 'windmill3','chennai', 150.456789,100.123456);

insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-15 06:02:55.388328+00', 2.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-15 06:14:10.969637+00', 1.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-15 06:20:08.48799+00', 2.5);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty','2019-10-15 06:28:02.565806+00' , 4.9);

insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-14 06:02:55.388328+00', 1.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-14 06:14:10.969637+00', 4.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-14 06:20:10.969637+00', 3.5);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-14 06:28:10.969637+00', 5.9);

insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-13 06:02:55.388328+00', 5.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-13 06:14:55.388328+00', 3.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-13 06:20:55.388328+00', 6.5);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerty', '2019-10-13 06:28:55.388328+00', 1.9);



insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta','2019-10-15 06:02:55.388328+00', 6.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta', '2019-10-15 06:14:10.969637+00', 4.4);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta', '2019-10-15 06:20:08.48799+00', 2.9);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta', '2019-10-15 06:28:02.565806+00', 4.4);

insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta', '2019-10-14 06:02:55.388328+00', 1.5);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta', '2019-10-14 06:14:10.969637+00', 3.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta', '2019-10-14 06:20:10.969637+00', 7.5);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta', '2019-10-14 06:28:10.969637+00', 1.9);

insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta', '2019-10-13 06:02:55.388328+00', 4.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta','2019-10-13 06:14:55.388328+00' , 5.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta','2019-10-13 06:20:55.388328+00' , 2.5);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwerta', '2019-10-13 06:28:55.388328+00', 3.9);




insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-15 06:02:55.388328+00', 7.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-15 06:14:10.969637+00', 1.4);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-15 06:20:08.48799+00', 3.9);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-15 06:28:02.565806+00', 2.4);

insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-14 06:02:55.388328+00', 4.5);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-14 06:14:10.969637+00', 5.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-14 06:20:10.969637+00', 1.2);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-14 06:28:10.969637+00', 3.3);

insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-13 06:02:55.388328+00' , 2.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-13 06:14:55.388328+00' , 4.3);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-13 06:20:55.388328+00' , 1.5);
insert into windmill_metric(serial_number, date_time, power) values ('1234567890qwertb', '2019-10-13 06:28:55.388328+00', 8.9);
