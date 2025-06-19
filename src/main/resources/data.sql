-- Insertar colaboradores (sin usuario asociado)
INSERT INTO colaborador (id, nombre, apellido, telefono, especialidad, rating_promedio, tickets_resueltos)
VALUES
  (1, 'Juan', 'Perez', '999111222', 'Soporte Tecnico', 4.5, 10),
  (2, 'Ana', 'Garcia', '999333444', 'Redes', 4.8, 20);

-- Insertar clientes (sin usuario asociado)
INSERT INTO clientes (id, nombre, apellido, email, telefono, area_departamento)
VALUES
  (1, 'Carlos', 'Ramirez', 'carlos@cliente.com', '988777666', 'Ventas'),
  (2, 'Lucia', 'Torres', 'lucia@cliente.com', '988555444', 'Compras');

-- Insertar usuarios asociados a colaboradores
INSERT INTO usuarios (id, email, password, role, last_login, created_at, colaborador_id, cliente_id)
VALUES
  (1, 'juan.perez@empresa.com', '$2a$12$UpIEZiVm3b2pfl6mOItiUu3tcFlB.OpKv/FbHwOxI6da3AVxKYKvS', 'ADMIN', NULL, NOW(), 1, NULL),
  (2, 'ana.garcia@empresa.com', '$2a$12$UpIEZiVm3b2pfl6mOItiUu3tcFlB.OpKv/FbHwOxI6da3AVxKYKvS', 'COLABORADOR', NULL, NOW(), 2, NULL);

-- Insertar usuarios asociados a clientes
INSERT INTO usuarios (id, email, password, role, last_login, created_at, colaborador_id, cliente_id)
VALUES
  (3, 'carlos@cliente.com', '$2a$12$UpIEZiVm3b2pfl6mOItiUu3tcFlB.OpKv/FbHwOxI6da3AVxKYKvS', 'CLIENTE', NULL, NOW(), NULL, 1),
  (4, 'lucia@cliente.com', '$2a$12$UpIEZiVm3b2pfl6mOItiUu3tcFlB.OpKv/FbHwOxI6da3AVxKYKvS', 'CLIENTE', NULL, NOW(), NULL, 2);

-- Insertar usuario sin asociacion (opcional)
INSERT INTO usuarios (id, email, password, role, last_login, created_at, colaborador_id, cliente_id)
VALUES
  (5, 'admin@empresa.com', '$2a$12$UpIEZiVm3b2pfl6mOItiUu3tcFlB.OpKv/FbHwOxI6da3AVxKYKvS', 'ADMIN', NULL, NOW(), NULL, NULL); 