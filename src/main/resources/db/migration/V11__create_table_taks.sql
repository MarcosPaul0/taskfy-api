CREATE TABLE IF NOT EXISTS taskfy_task (
  id SERIAL PRIMARY KEY,
  user_id TEXT REFERENCES taskfy_user(id) ON DELETE CASCADE,
  task_group_id TEXT REFERENCES taskfy_task_group(id) ON DELETE CASCADE NOT NULL,
  title VARCHAR(100) NOT NULL,
  description VARCHAR(500),
  due_date TIMESTAMP,
  status VARCHAR(11) NOT NULL,
  points INTEGER NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
