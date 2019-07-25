#QUERIES
#&&1
select name from airline_oltp where country like 'France ';
select name from airline_olap where country like 'France ';

#&&2
select r.* from route_oltp as r, airline_oltp as al, airport_oltp as a1, airport_oltp as a2, plane_oltp as p
where r.airline_id = al.airline_id and r.src_airport_id = a1.airport_id and r.dst_airport_id = a2.airport_id
and r.plane = p.plane_id and al.country like 'United States ' and a1.name like '%Miami%' and a2.name like '%Detroit%'
and p.name like '%Airbus%';

select r.* from route_olap as r, airline_olap as al, airport_olap as a1, airport_olap as a2
where r.airline_id = al.airline_id and r.src_airport_id = a1.airport_id and r.dst_airport_id = a2.airport_id
and al.country like 'United States ' and a1.name like '%Miami%' and a2.name like '%Detroit%' and r.name like '%Airbus%';

#&&3
insert into airline_oltp values(1,'Aa', 'Bb', 'Cc', 'Ddd', 'Italy ');

insert into airline_olap values(1, 'Aa', 'Bb', 'Cc', 'Ddd', 'Italy ', 'IT', 'I');

#&&4
update airline_oltp set iata = 'Ab' where airline_id = 1;

update airline_olap set iata = 'Ab' where airline_id = 1;

#&&5
delete from airline_oltp where airline_id = 1;

delete from airline_olap where airline_id = 1;