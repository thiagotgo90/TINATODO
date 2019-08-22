#!/bin/bash
git clone --single-branch --branch backend-endpoints https://github.com/thiagotgo90/TINATODO.git
cd TINATODO
mvn install
# java -jar target/tinatodo-0.0.1-SNAPSHOT.jar