-- Drop the database if it exists
DROP DATABASE IF EXISTS p2_sa_notification_db;

-- Create the database
CREATE DATABASE p2_sa_notification_db;

-- Create the user / role (login) if it does not already exist
DO
$$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_catalog.pg_roles
      WHERE rolname = 'p2_sa_notification_user'
   ) THEN
      CREATE ROLE p2_sa_notification_user WITH LOGIN PASSWORD 'notification_service';
   END IF;
END
$$;

-- Grant all privileges on the database to the user
GRANT ALL PRIVILEGES ON DATABASE p2_sa_notification_db TO p2_sa_notification_user;

GRANT USAGE ON SCHEMA public TO p2_sa_notification_user;
GRANT CREATE ON SCHEMA public TO p2_sa_notification_user;

ALTER DATABASE p2_sa_notification_db OWNER TO p2_sa_notification_user;