#!/bin/bash

psql -v ON_ERROR_STOP=1 --username pgadmin --dbname pgadmin <<-EOSQL

  CREATE DATABASE rightdevops WITH OWNER pgadmin;
EOSQL

BACKUP_DIR=/docker-entrypoint-initdb.d/db_backup

DATABASES=(
  "rightdevops"
)

export PGPASSWORD=pgadmin

#for db in "${DATABASES[@]}"; do
#  psql -U pgadmin -d "$db" < "$BACKUP_DIR/$db.sql"
#done
