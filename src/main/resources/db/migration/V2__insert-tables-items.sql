insert into groups values (1, 'Antenas')
insert into sub_groups values (1, 'Antena Digital', 1)
insert into brands values (1, 'Century')
insert into unit_measure values (1, 'UN')
insert into type_items values(1, '00 - Mercadoria para Revenda')
insert into taxation_tables (name) values ('Mercadoria Tributada Normalmente')
insert into tax_groups values (1, 'Mercadoria Tributada Normalmente', 1)
insert into items_class values (1, 'Geral')

insert into items values (
  1,
  '43242344234',
  '2026-02-13 11:34:57.888778',
  'img.png',
  'Produto Exemplo',
  200,
  180,
  '2026-02-13 11:34:57.888778',
  1,
  1,
  1,
  1,
  1,
  1);

select *from items