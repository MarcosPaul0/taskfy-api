CREATE TABLE IF NOT EXISTS taskfy_reward (
  id TEXT PRIMARY KEY UNIQUE NOT NULL,
  ranking_id TEXT REFERENCES taskfy_ranking(id) ON DELETE CASCADE NOT NULL,
  name VARCHAR(200) NOT NULL,
  image_link TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);
