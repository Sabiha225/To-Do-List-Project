drop table if exists todolist CASCADE;
create table if not exists Todolist (id integer, task varchar(400) not null, primary key (id));
