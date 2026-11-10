CREATE DATABASE IF NOT EXISTS auth_db;
CREATE DATABASE IF NOT EXISTS passages_db;

-- En MySQL 8 para desarrollo, es más limpio asegurar los privilegios globales de root
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;
