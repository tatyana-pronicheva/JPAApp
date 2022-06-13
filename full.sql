START TRANSACTION;
DROP TABLE IF EXISTS products_list;
CREATE TABLE products_list (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(50), cost INT);
INSERT INTO products_list (title, cost) VALUES ('Milk', 50), ('Bread', 20), ('Meat', 200), ('Butter', 200), ('Eggs', 100),('Sugar', 50), ('Bananas', 30), ('Salt', 15), ('Pepper', 450), ('Rice', 100),('Pie', 50), ('Ice cream', 20), ('Cheese', 200), ('Peanuts', 700), ('Cabbage', 70),('Beetroot', 85), ('Onion', 34), ('Garlic', 70), ('Basil', 200), ('Olives', 100);
COMMIT;