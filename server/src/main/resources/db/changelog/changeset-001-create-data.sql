CREATE TABLE user_data (
    id SERIAL PRIMARY KEY,
    userid VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    recorded_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Index for efficient lookups by userid and metric name
CREATE INDEX idx_user_metrics_userid_name ON user_data (userid, name);