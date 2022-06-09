drop table if exists toDolist CASCADE;
create table if not exists ToDolist (id integer AUTO_INCREMENT, task varchar(400) not null, primary key (id));
