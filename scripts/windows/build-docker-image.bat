@echo off

call mvn clean package -DskipTests

docker build -t git2rdflab/listener-service:0.0.1-SNAPSHOT .
