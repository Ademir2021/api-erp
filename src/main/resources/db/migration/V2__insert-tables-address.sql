INSERT INTO state VALUES (1, 'PR', 'Parana')
INSERT INTO countries VALUES (1, 'BRA', '1.111', '1.222' , '55', 'Brasil')
INSERT INTO cities VALUES (1, '4102505', 'Barbosa Ferraz', 1, 1)
INSERT INTO zip_codes VALUES  (1, '86960000', 1)
SELECT *FROM state
SELECT *FROM countries
SELECT *FROM cities
select *from zip_codes

INSERT INTO address (complement, neighborhood, number, street, zipcode_id) VALUES (
  'Loja',
  'Centro',
  '1241',
  'Av, Castro Alves',
  1
);
select *from address