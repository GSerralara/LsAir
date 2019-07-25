#USERS

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