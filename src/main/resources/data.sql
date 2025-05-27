-- Password: admin123 (BCrypt encoded)
INSERT INTO usuarios (email, password, role, created_at) VALUES
('admin@demo.pe', '$2a$10$LMytKQ.ZIXmj/k6TzXPHzeC3Yxv1xUyIWXxoMTuHFKKmN3RBD9iGG', 'ROLE_ADMIN', NOW()),
('colab@demo.pe', '$2a$10$LMytKQ.ZIXmj/k6TzXPHzeC3Yxv1xUyIWXxoMTuHFKKmN3RBD9iGG', 'ROLE_COLABORADOR', NOW()),
('client@demo.pe', '$2a$10$LMytKQ.ZIXmj/k6TzXPHzeC3Yxv1xUyIWXxoMTuHFKKmN3RBD9iGG', 'ROLE_CLIENTE', NOW());

-- Create client records
INSERT INTO clientes (nombre, apellido, email, telefono, area_departamento, usuario_id) 
SELECT 'Juan', 'Cliente', email, '987654321', 'Ventas', id
FROM usuarios WHERE email = 'client@demo.pe';

-- Create collaborator records
INSERT INTO colaborador (nombre, apellido, especialidad, telefono, usuario_id) 
SELECT 'Pedro', 'Tecnico', 'Soporte Tecnico', '123456789', id
FROM usuarios WHERE email = 'colab@demo.pe';

