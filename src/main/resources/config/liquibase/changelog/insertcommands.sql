--liquibase formatted sql
--changeset {pickList}:{id}
insert into pick_list (id, pick_list_name, status, display_label_name, created_by) values (10, 'Financial Year', 1, 'YEAR', "anonymousUser");
insert into pick_list (id, pick_list_name, status, display_label_name, created_by) values (11, 'Nursery Type', 1, 'NURSERY TYPE', "anonymousUser");
