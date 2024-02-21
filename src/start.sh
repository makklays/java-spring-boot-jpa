#!/bin/bash

# Pull new changes
git pull

# Prepare Jar
mvn clean
mvn package

# Ensure, that docker-compose stopped
docker-compose stop

# Add environment variables
export BOT_NAME=$1
export BOT_TOKEN=$2
export BOT_DB_USERNAME='prod_jpa_db_user'
export BOT_DB_PASSWORD='2342d23424234234'

# Start new deployment
docker-compose up --build -d