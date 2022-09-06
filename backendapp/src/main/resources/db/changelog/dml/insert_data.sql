--liquibase formatted sql

--changeset tpronicheva:insert_data_in_products

INSERT INTO products (title, description, price, amount)
VALUES ('Milk', 'This is tasty', 50, 1500),
 ('Bread', 'Fresh bread', 20, 2000),
 ('Meat', 'Pork', 200, 200),
 ('Butter','Salty butter', 200, 500),
 ('Eggs', '10pcs in the box', 100, 200),
 ('Sugar', '1 kg', 50, 1000),
 ('Bananas', 'from France', 30, 300),
 ('Salt', 'Black salt', 15, 1200),
 ('Pepper', 'Red fresh pepper', 450, 200),
 ('Rice', 'Jasmin', 100, 400),
 ('Pie', 'With peaches', 50, 500),
 ('Ice cream', 'Vanilla milk ice cream', 20, 300),
 ('Cheese', 'Goat cheese from Africa', 500, 200),
 ('Basil', 'Fresh green basil', 200, 20),
 ('Olives', 'Green olives with lemon inside', 100, 200);

