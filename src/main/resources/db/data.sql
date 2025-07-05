-- Script de datos para la base de datos
-- Este archivo se ejecuta automáticamente al iniciar la aplicación

-- Insertar categorías
INSERT INTO categoria (nombre) VALUES 
('Electrónicos'),
('Ropa'),
('Hogar'),
('Deportes'),
('Libros');

-- Insertar productos para la categoría Electrónicos
INSERT INTO producto (nombre, precio, id_categoria) VALUES 
('Smartphone Samsung Galaxy S23', 899.99, 1),
('Laptop Dell Inspiron 15', 1299.99, 1),
('Tablet iPad Air', 599.99, 1),
('Auriculares Sony WH-1000XM4', 349.99, 1),
('Smart TV LG 55" 4K', 799.99, 1),
('Cámara Canon EOS R6', 2499.99, 1),
('Consola PlayStation 5', 499.99, 1),
('Monitor Dell 27" 4K', 399.99, 1),
('Teclado mecánico Logitech', 129.99, 1),
('Mouse gaming Razer', 89.99, 1),
('Altavoces Bluetooth JBL', 199.99, 1),
('Cargador inalámbrico Samsung', 49.99, 1);

-- Insertar productos para la categoría Ropa
INSERT INTO producto (nombre, precio, id_categoria) VALUES 
('Camiseta básica algodón', 19.99, 2),
('Jeans Levi\'s 501', 89.99, 2),
('Vestido elegante negro', 129.99, 2),
('Chaqueta de cuero', 299.99, 2),
('Zapatillas Nike Air Max', 149.99, 2),
('Polo Ralph Lauren', 79.99, 2),
('Falda midi floral', 59.99, 2),
('Traje formal azul', 399.99, 2),
('Sudadera con capucha', 45.99, 2),
('Pantalones deportivos', 35.99, 2),
('Blazer casual', 159.99, 2),
('Camisa formal blanca', 69.99, 2);

-- Insertar productos para la categoría Hogar
INSERT INTO producto (nombre, precio, id_categoria) VALUES 
('Sofá de 3 plazas', 899.99, 3),
('Mesa de comedor', 599.99, 3),
('Silla de oficina ergonómica', 299.99, 3),
('Lámpara de mesa LED', 89.99, 3),
('Cafetera automática', 199.99, 3),
('Licuadora Oster', 79.99, 3),
('Aspiradora robot', 399.99, 3),
('Juego de sábanas algodón', 49.99, 3),
('Cortinas blackout', 129.99, 3),
('Alfombra persa', 299.99, 3),
('Espejo de pared', 159.99, 3),
('Maceta decorativa', 29.99, 3);

-- Insertar productos para la categoría Deportes
INSERT INTO producto (nombre, precio, id_categoria) VALUES 
('Pelota de fútbol oficial', 89.99, 4),
('Raqueta de tenis Wilson', 199.99, 4),
('Bicicleta de montaña', 899.99, 4),
('Pesas ajustables', 299.99, 4),
('Colchoneta de yoga', 39.99, 4),
('Guantes de boxeo', 79.99, 4),
('Casco de ciclismo', 149.99, 4),
('Botella de agua deportiva', 24.99, 4),
('Mochila deportiva', 89.99, 4),
('Reloj deportivo Garmin', 399.99, 4),
('Cuerda para saltar', 19.99, 4),
('Bandas de resistencia', 34.99, 4);

-- Insertar productos para la categoría Libros
INSERT INTO producto (nombre, precio, id_categoria) VALUES 
('El Señor de los Anillos', 29.99, 5),
('Cien años de soledad', 24.99, 5),
('Don Quijote de la Mancha', 19.99, 5),
('Harry Potter y la piedra filosofal', 22.99, 5),
('1984 de George Orwell', 18.99, 5),
('El Principito', 15.99, 5),
('Los miserables', 27.99, 5),
('Orgullo y prejuicio', 21.99, 5),
('El hobbit', 25.99, 5),
('Crimen y castigo', 23.99, 5),
('Madame Bovary', 20.99, 5),
('Los hermanos Karamazov', 26.99, 5); 