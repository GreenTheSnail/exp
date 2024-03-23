CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS "user"
(
    id                   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name                 VARCHAR(255) NOT NULL,
    surname              VARCHAR(255) NOT NULL,
    identificationNumber VARCHAR(255) NOT NULL UNIQUE
);
