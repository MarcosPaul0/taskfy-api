CREATE TABLE IF NOT EXISTS taskfy_reward (
  id TEXT PRIMARY KEY UNIQUE NOT NULL,
  ranking_id TEXT REFERENCES taskfy_ranking(id) ON DELETE CASCADE NOT NULL,
  image_url TEXT,
  title VARCHAR(50) NOT NULL,
  description VARCHAR(300),
  position INTEGER CHECK (position >= 1),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT unique_ranking_position UNIQUE (ranking_id, position)
);
