CREATE TYPE taskfy_role AS ENUM ('admin', 'normal');

ALTER TABLE taskfy_user ADD COLUMN role taskfy_role NOT NULL DEFAULT 'normal';