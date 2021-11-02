This service is a recipe catalog.

For start application you need to do: 

1. Create database and role recipe_catalog: 

```sql
CREATE DATABASE recipe_catalog;
CREATE ROLE recipe_catalog WITH LOGIN ENCRYPTED PASSWORD 'recipe_catalog';
```

script for deleting and re-creating the database 
(the first line kills all current database connections):
```sql
SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = 'recipe_catalog' AND pid<>pg_backend_pid();
DROP DATABASE recipe_catalog;
CREATE DATABASE recipe_catalog;
DROP USER recipe_catalog;
CREATE ROLE recipe_catalog WITH LOGIN ENCRYPTED PASSWORD 'recipe_catalog';
GRANT ALL PRIVILEGES ON DATABASE recipe_catalog TO recipe_catalog;
```