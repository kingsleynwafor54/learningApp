create database if not exists iwedb;

create user if not exists 'ile-dev'@'localhost' identified by 'IleIwe123';
grant all privileges on iwedb.* to 'ile-dev'@'localhost';
flush privileges;