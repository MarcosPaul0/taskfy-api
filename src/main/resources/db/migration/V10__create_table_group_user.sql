--CREATE TYPE IF NOT EXISTS taskfy_task_group_user_role AS ENUM ('owner', 'manager', 'normal');
--CREATE TYPE IF NOT EXISTS taskfy_invite_status AS ENUM ('pending', 'accepted', 'rejected');
--CREATE TYPE IF NOT EXISTS taskfy_request_status AS ENUM ('pending', 'accepted', 'rejected');

CREATE TABLE IF NOT EXISTS taskfy_task_group_user (
  id SERIAL PRIMARY KEY,
  user_id TEXT REFERENCES taskfy_user(id) ON DELETE CASCADE NOT NULL,
  task_group_id TEXT REFERENCES taskfy_task_group(id) ON DELETE CASCADE NOT NULL,
  task_group_role VARCHAR(7) DEFAULT 'normal' NOT NULL,
  invite_status VARCHAR(10),
  request_status VARCHAR(10),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT unique_user_task_group_pair UNIQUE (user_id, task_group_id)
);
