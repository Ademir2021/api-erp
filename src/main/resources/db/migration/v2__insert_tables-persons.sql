INSERT INTO persons VALUES(
  1, 45,
  '18069383000110',
  '74276697972',
  '2026-01-28 20:16:57.888778',
  '2026-01-28 20:16:57.888778',
  'centroserra@gmail.com',
  '0',
  'Isento',
  'Ademir Souza de Almeida',
  '7113415434',
  '2026-01-28 20:16:57.888778',
  1, 1, 0, 1);
SELECT *FROM persons;

insert into group_persons values (1, 'Cliente');
insert into group_persons values (2, 'Fornecedor');
insert into group_persons values (3, 'Transportador');
insert into group_persons values (4, 'Funcionario');
insert into group_persons values (5, 'Outros');

select * from group_persons
