-- cardeneta.user_roles definition

-- Drop table

-- DROP TABLE cardeneta.user_roles;

CREATE TABLE cardeneta.user_roles (
    role_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT user_roles_pkey PRIMARY KEY (role_id, user_id),
    FOREIGN KEY (role_id) REFERENCES cardeneta.roles(id),
    FOREIGN KEY (user_id) REFERENCES cardeneta.users(id)
);


-- cardeneta.user_roles foreign keys