-- Change Set 202408081200-2
-- Author: Vitalii

-- Insert permissions
INSERT INTO permission (name) VALUE ('READ')
ON DUPLICATE KEY UPDATE name = name;
INSERT INTO permission (name) VALUE ('WRITE')
ON DUPLICATE KEY UPDATE name = name;
INSERT INTO permission (name) VALUE ('UPDATE')
ON DUPLICATE KEY UPDATE name = name;
INSERT INTO permission (name) VALUE ('CREATE')
ON DUPLICATE KEY UPDATE name = name;
INSERT INTO permission (name) VALUE ('DELETE')
ON DUPLICATE KEY UPDATE name = name;

-- Associate Admin Role with all permissions
INSERT INTO role_permissions (role_id, permission_id)
VALUES ((SELECT r.id FROM roles r WHERE r.name = 'ROLE_ADMIN'), (SELECT p.id FROM permission p WHERE p.name = 'READ'));

INSERT INTO role_permissions (role_id, permission_id)
VALUES ((SELECT r.id FROM roles r WHERE r.name = 'ROLE_ADMIN'), (SELECT p.id FROM permission p WHERE p.name = 'WRITE'));

INSERT INTO role_permissions (role_id, permission_id)
VALUES ((SELECT r.id FROM roles r WHERE r.name = 'ROLE_ADMIN'), (SELECT p.id FROM permission p WHERE p.name = 'UPDATE'));

INSERT INTO role_permissions (role_id, permission_id)
VALUES ((SELECT r.id FROM roles r WHERE r.name = 'ROLE_ADMIN'), (SELECT p.id FROM permission p WHERE p.name = 'CREATE'));

INSERT INTO role_permissions (role_id, permission_id)
VALUES ((SELECT r.id FROM roles r WHERE r.name = 'ROLE_ADMIN'), (SELECT p.id FROM permission p WHERE p.name = 'DELETE'));

-- For testing purposes associate User Role with READ permission
INSERT INTO role_permissions (role_id, permission_id)
VALUES ((SELECT r.id FROM roles r WHERE r.name = 'ROLE_USER'), (SELECT p.id FROM permission p WHERE p.name = 'READ'));