CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users.users(
    id          uuid            default uuid_generate_v4(),
    name        varchar not null,
    password    varchar not null,
    description varchar not null
);

ALTER TABLE users.users ADD PRIMARY KEY(id);