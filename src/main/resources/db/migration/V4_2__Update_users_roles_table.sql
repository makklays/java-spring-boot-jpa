
-- Associate Admin User with Admin Role
INSERT INTO users_roles (users_id, roles_id)
SELECT u.id, r.id
FROM users u, roles r
WHERE u.email = 'admin' AND r.name = 'ADMIN';

