CREATE TABLE IF NOT EXISTS app_user
(
    id         BIGINT(20)   UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email      VARCHAR(64)  NOT NULL DEFAULT '',
    first_name VARCHAR(128) NOT NULL DEFAULT '',
    last_name  VARCHAR(128) NOT NULL DEFAULT '',
    phone      VARCHAR(128) NOT NULL DEFAULT '',
    created_at DATETIME     NOT NULL DEFAULT current_timestamp(),
    updated_at DATETIME     NOT NULL DEFAULT current_timestamp()
                            ON UPDATE current_timestamp(),
    created_by BIGINT(20)   UNSIGNED NOT NULL DEFAULT 1,
    updated_by BIGINT(20)   UNSIGNED NOT NULL DEFAULT 1,
    CONSTRAINT email         UNIQUE (email)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;