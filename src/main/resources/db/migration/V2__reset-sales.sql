select *from cash_movement
select *from accounts_receivable
select *from items_sales
select *from sales
  
delete from cash_movement
delete from accounts_receivable
delete from items_sales
delete from sales
  
alter sequence cash_movement_id_seq restart with 1
alter sequence accounts_receivable_id_seq restart with 1
alter sequence sales_id_seq restart with 1
alter sequence items_sales_id_seq restart with 1