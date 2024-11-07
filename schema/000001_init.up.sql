CREATE TABLE users
(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE verifications
(
    user_id INTEGER PRIMARY KEY REFERENCES users(id),
    verification_code VARCHAR(255),
    verification_code_expires_at TIMESTAMP,
    is_verified BOOLEAN DEFAULT FALSE
);
