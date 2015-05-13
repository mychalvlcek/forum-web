
-- destinations data --
INSERT INTO destinations (id, `name`, lat, lon) VALUES (1, 'Prague/PRG', 50.1007996, 14.2600002);
INSERT INTO destinations (id, `name`, lat, lon) VALUES (2, 'London/LHR', 51.4706001, -0.461941);
INSERT INTO destinations (id, `name`, lat, lon) VALUES (3, 'London/STN', 51.8849983, 0.235);
INSERT INTO destinations (id, `name`, lat, lon) VALUES (4, 'Edinburgh/EDI', 55.9500008, -3.3724999);
INSERT INTO destinations (id, `name`, lat, lon) VALUES (5, 'Beijing/PEK', 40.080101, 116.5849991);
INSERT INTO destinations (id, `name`, lat, lon) VALUES (6, 'Tokyo/HND', 35.5522995, 139.7799988);
INSERT INTO destinations (id, `name`, lat, lon) VALUES (7, 'Chicago/ORD', 41.9785996, -87.9048004);
INSERT INTO destinations (id, `name`, lat, lon) VALUES (8, 'Los Angeles/LAX', 33.9425011, -118.4079971);
INSERT INTO destinations (id, `name`, lat, lon) VALUES (9, 'Dubai/DXB', 25.2528, 55.364399);
INSERT INTO destinations (id, `name`, lat, lon) VALUES (10, 'Frankfurt/FRA', 50.0264015, 8.5431299);

-- flights data --
INSERT INTO flights (id, date_of_departure, distance, `name`, price, seats, dest_from, dest_to) VALUES (1, '2014-10-25 07:20:00', 1047, 'PRG - LHR', 3200, 300, 1, 2);
