#PROCEDURES

DELIMITER $$

DROP PROCEDURE IF EXISTS creacioEstructures $$
CREATE PROCEDURE creacioEstructures()
BEGIN
	
    DROP TABLE IF EXISTS airline_OLAP CASCADE;
	CREATE TABLE airline_OLAP(
		airline_id		int(11) NOT NULL,
		name			varchar(4) DEFAULT NULL,
		alias			varchar(5) DEFAULT NULL,
		iata			text,
		icao			text,
		country			varchar(255) DEFAULT NULL,
		code			varchar(5) DEFAULT NULL,
		dst				char(4) DEFAULT NULL,
		PRIMARY KEY(airline_id)
	);

	DROP TABLE IF EXISTS airport_OLAP CASCADE;
	CREATE TABLE airport_OLAP(
		airport_id				int(11) NOT NULL,
		name					text,
		city					varchar(255) DEFAULT NULL,
		country					varchar(255) DEFAULT NULL,
		code					varchar(5) DEFAULT NULL,
		dst						char(4) DEFAULT NULL,
		iata					text,
		icao					text,
		latitude				text,
		longitude				double DEFAULT NULL,
		altitude				double DEFAULT NULL,
		timezone_id				int(11) DEFAULT NULL,
		timezone_olson			varchar(255) DEFAULT NULL,
		timezone_utc			double DEFAULT NULL,
		daylight_saving_time	char(1) DEFAULT NULL,
		PRIMARY KEY(airport_id)
	);

	DROP TABLE IF EXISTS route_OLAP CASCADE;
	CREATE TABLE route_OLAP(
		route_id				int(11) NOT NULL,
		plane					int(11) NOT NULL,
		airline_id				int(11) NOT NULL,
		src_airport_id			int(11) NOT NULL,
		dst_airport_id			int(11) NOT NULL,
		codeshare				text,
		stops					text,
		name					varchar(255) DEFAULT NULL,
		iata					text,
		icao					text,
		PRIMARY KEY(route_id, airline_id, src_airport_id, dst_airport_id),
        FOREIGN KEY(airline_id) REFERENCES Airline_OLAP(airline_id),
        FOREIGN KEY(src_airport_id) REFERENCES Airport_OLAP(airport_id),
        FOREIGN KEY(dst_airport_id) REFERENCES Airport_OLAP(airport_id)
	);

	DROP TABLE IF EXISTS country_OLTP CASCADE;
	CREATE TABLE country_OLTP(
		country					varchar(255) NOT NULL,
		code					varchar(5) DEFAULT NULL,
		dst						varchar(4) DEFAULT NULL,
        PRIMARY KEY(country)
	);

	DROP TABLE IF EXISTS city_OLTP CASCADE;
	CREATE TABLE city_OLTP(
		country			varchar(255) NOT NULL,
		city			varchar(255) NOT NULL,
        PRIMARY KEY(country, city),
        FOREIGN KEY(country) REFERENCES country_OLTP(country)
	);

	DROP TABLE IF EXISTS airport_OLTP CASCADE;
	CREATE TABLE airport_OLTP(
		airport_id				int(11),
		name					text,
		city					varchar(255) DEFAULT NULL,
		country					varchar(255) DEFAULT NULL,
		iata					text,
		icao					text,
		latitude				text,
		longitude				double DEFAULT NULL,
		altitude				double DEFAULT NULL,
        PRIMARY KEY(airport_id),
        FOREIGN KEY(country, city) REFERENCES city_OLTP(country, city)
	);

	DROP TABLE IF EXISTS airline_OLTP CASCADE;
	CREATE TABLE airline_OLTP(
		airline_id				int(11) NOT NULL,
		name					varchar(4) DEFAULT NULL,
		alias					varchar(5) DEFAULT NULL,
		iata					text,
		icao					text,
		country 				varchar(255) DEFAULT NULL,
        PRIMARY KEY(airline_id),
        FOREIGN KEY(country) REFERENCES country_OLTP(country)
	);

	DROP TABLE IF EXISTS plane_OLTP CASCADE;
	CREATE TABLE plane_OLTP(
		plane_id				int(11) NOT NULL,
		name					varchar(255) DEFAULT NULL,
		iata_code				text,
		icao_code				text,
        PRIMARY KEY(plane_id)
	);

	DROP TABLE IF EXISTS route_OLTP CASCADE;
	CREATE TABLE route_OLTP(
		route_id				int(11) NOT NULL,
		airline_id				int(11) NOT NULL,
		src_airport_id			int(11) NOT NULL,
		dst_airport_id			int(11) NOT NULL,
		codeshare				text,
		stops					text,
		plane					int(11) NOT NULL,
        PRIMARY KEY(route_id, airline_id, src_airport_id, dst_airport_id, plane),
        FOREIGN KEY(airline_id) REFERENCES airline_OLTP(airline_id),
        FOREIGN KEY(src_airport_id) REFERENCES airport_OLTP(airport_id),
        FOREIGN KEY(dst_airport_id) REFERENCES airport_OLTP(airport_id),
        FOREIGN KEY(plane) REFERENCES plane_oltp(plane_id)
	);

	DROP TABLE IF EXISTS timezone_OLTP CASCADE;
	CREATE TABLE timezone_OLTP(
		timezone_id				int(11) NOT NULL,
		timezone_olson			varchar(255) DEFAULT NULL,
		timezone_utc			double DEFAULT NULL,
		daylight_saving_time	char(1) DEFAULT NULL,
        PRIMARY KEY(timezone_id)
	);

	DROP TABLE IF EXISTS timezone_city_OLTP CASCADE;
	CREATE TABLE timezone_city_OLTP(
		country					varchar(255) NOT NULL,
		city					varchar(255) NOT NULL,
		timezone_id				int(11) NOT NULL,
        PRIMARY KEY(country, city, timezone_id),
        FOREIGN KEY(country, city) REFERENCES city_OLTP(country, city),
        FOREIGN KEY(timezone_id) REFERENCES timezone_OLTP(timezone_id)
	);
    
END $$


DROP PROCEDURE IF EXISTS eliminaOLTP $$
CREATE PROCEDURE eliminaOLTP()
BEGIN
    
    DROP TABLE IF EXISTS route_OLTP CASCADE;
    DROP TABLE IF EXISTS timezone_city_OLTP CASCADE;
    DROP TABLE IF EXISTS airline_OLTP CASCADE;
    DROP TABLE IF EXISTS airport_OLTP CASCADE;
    DROP TABLE IF EXISTS city_OLTP CASCADE;
    DROP TABLE IF EXISTS plane_OLTP CASCADE;
    DROP TABLE IF EXISTS country_OLTP CASCADE;
    DROP TABLE IF EXISTS timezone_OLTP CASCADE;

END $$

DROP PROCEDURE IF EXISTS eliminaOLAP $$
CREATE PROCEDURE eliminaOLAP()
BEGIN
	
	DROP TABLE IF EXISTS route_OLAP CASCADE;
    	DROP TABLE IF EXISTS airline_OLAP CASCADE;
   	DROP TABLE IF EXISTS airport_OLAP CASCADE;

END $$

DROP PROCEDURE IF EXISTS importacio $$
CREATE PROCEDURE importacio()
BEGIN

	delete from airline_olap;
    	delete from airport_olap;
    	delete from route_olap;

	insert into airline_OLAP(airline_id, name, alias, iata, icao, country) select * from airline_OLTP;
		update airline_OLAP as a, country_OLTP as c set a.code = c.code, a.dst = c.dst where a.country = c.country and a.airline_id = a.airline_id;

	insert into airport_OLAP(airport_id, name, city, country, iata, icao, latitude, longitude, altitude) select * from airport_OLTP;
		update airport_OLAP as a, country_OLTP as c set a.code = c.code, a.dst = c.dst where a.country = c.country;
		update airport_OLAP as a , timezone_OLTP as t, timezone_city_OLTP as tc set a.timezone_id =t.timezone_id, 
        a.timezone_olson = t.timezone_olson, a.timezone_utc = t.timezone_utc,
		a.daylight_saving_time = t.daylight_saving_time where tc.timezone_id = t.timezone_id and a.city = tc.city;
		
	insert into route_OLAP(route_id,airline_id, src_airport_id, dst_airport_id, codeshare, stops, plane) select * from route_OLTP;
		update route_OLAP as r, plane_OLTP as p set r.name = p.name, r.iata = p.iata_code, r.icao = p.icao_code 
        	where r.plane = p.plane_id;
		
END $$

DROP PROCEDURE IF EXISTS creacioUsers $$
CREATE PROCEDURE creacioUsers()
BEGIN
	
    create user 'analytic_user';
    grant select on lsair.airline_olap to 'analytic_user';
    grant select on lsair.airport_olap to 'analytic_user';
    grant select on lsair.route_olap to 'analytic_user';
    grant create view on lsair.airline_olap to 'analytic_user';
    grant create view on lsair.airport_olap to 'analytic_user';
    grant create view on lsair.route_olap to 'analytic_user';
    grant show view on lsair.airline_olap to 'analytic_user';
    grant show view on lsair.airport_olap to 'analytic_user';
    grant show view on lsair.route_olap to 'analytic_user';
    
    create user 'manager_user';
    grant select on lsair.* to 'manager_user';
    grant update on lsair.* to 'manager_user';
    grant insert on lsair.* to 'manager_user';
    
    create user 'rrhh_user';
    grant create user on *.* to 'rrhh_user';
		
END $$

DELIMITER ;