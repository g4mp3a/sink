  
-- ------ SALES ------

TRUNCATE TABLE categories;
TRUNCATE TABLE products;
TRUNCATE TABLE products_in_categories;

INSERT INTO categories VALUES
    ('1', 'apples', 'apples'),
    ('2', 'oranges', 'oranges'),
    ('3', 'others', 'others');

INSERT INTO products VALUES
    ('1', 'Apples 1', 'Apples 1', 1.00),
    ('2', 'Oranges 2', 'Oranges 2', 2.50),
    ('3', 'Apples 2', 'Apples 2', 2.50),
    ('4', 'Oranges 1', 'Oranges 1', 1.00);

INSERT INTO products_in_categories VALUES
    ('1', '1'),
    ('2', '1'),
    ('3', '1'),
    ('4', '2');

-- ------ WAREHOUSE ------

TRUNCATE TABLE products_in_stock;

INSERT INTO products_in_stock VALUES
    ('1', 5),
    ('2', 0),
    ('3', 13),
    ('4', 55);