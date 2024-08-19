
-- Insert Admin Role (if not exists)
INSERT INTO roles (name) VALUES ('ADMIN') ON DUPLICATE KEY UPDATE name = name;

-- Insert Admin User
INSERT INTO users (email, password) VALUES ('admin', '$2a$12$sL/ppYTnUBDV47MZnTypJetXJ3JuZDDr.LchUSwfl8PzH2xHd1Tvm');

-- Associate Admin User with Admin Role
INSERT INTO users_roles (users_id, roles_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.email = 'admin' AND r.name = 'ADMIN';

