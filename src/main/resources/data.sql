-- Contraseña: password123 (BCrypt encoded)
INSERT INTO usuarios (email, password, role, created_at) VALUES
('admin@demo.pe', '$2a$10$rPiEAgSK0g5hGT/4tlYUp.hy5qVx2h2qHkVZCCUPRYXnRmONJE4e2', 'ADMIN', NOW()),
('colab@demo.pe', '$2a$10$rPiEAgSK0g5hGT/4tlYUp.hy5qVx2h2qHkVZCCUPRYXnRmONJE4e2', 'COLABORADOR', NOW()),
('client@demo.pe', '$2a$10$rPiEAgSK0g5hGT/4tlYUp.hy5qVx2h2qHkVZCCUPRYXnRmONJE4e2', 'CLIENTE', NOW());

-- Crear registros de colaborador y cliente
INSERT INTO colaborador (nombre, apellido, especialidad, usuario_id) 
SELECT 'Juan', 'Perez', 'Soporte Tecnico', id 
FROM usuarios WHERE email = 'colab@demo.pe';

