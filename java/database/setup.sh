#!/bin/bash

# Connect to PostgreSQL and create database
docker exec -i restaurant-db psql -U restaurant_user -d restaurant_finder << EOF
\i /docker-entrypoint-initdb.d/new_schema.sql
\i /docker-entrypoint-initdb.d/restaurants.sql
EOF 