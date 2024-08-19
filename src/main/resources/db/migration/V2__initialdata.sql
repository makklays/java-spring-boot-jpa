-- Change Set 202408081200-2
-- Author: Vitalii

INSERT INTO positions (title, description, created_at, updated_at) VALUE ('Marinero', 'Description un marinero para algun barco.', '2024-02-21 18:19:03', '2024-02-21 18:19:03');
INSERT INTO positions (title, description, created_at, updated_at) VALUE ('Pisition 2', 'Description position 2', '2024-02-21 18:20:04', '2024-02-21 18:20:04');
INSERT INTO positions (title, description, created_at, updated_at) VALUE ('Pisition 3', 'Description position 3', '2024-02-21 18:21:05', '2024-02-21 18:21:05');

-- Insert Admin Role (if not exists)
INSERT INTO roles (name) VALUES ('ROLE_ADMIN') ON DUPLICATE KEY UPDATE name = name;
INSERT INTO roles (name) VALUES ('ROLE_USER') ON DUPLICATE KEY UPDATE name = name;

-- Insert Admin User
INSERT INTO users (email, password) VALUES ('admin', '$2a$12$sL/ppYTnUBDV47MZnTypJetXJ3JuZDDr.LchUSwfl8PzH2xHd1Tvm');

-- Associate Admin User with Admin Role
INSERT INTO user_roles (user_id, role_id) VALUES (
    (SELECT u.id FROM users u WHERE u.email = 'admin'), (SELECT r.id FROM roles r WHERE r.name = 'ROLE_ADMIN')
);

--
INSERT INTO barcos (title, description, `year`, `weight`, `speedometer`,user_id) VALUES ("TUAPSE", "Description for bacro TUAPSE", "1905", "100100", "87000",1);


