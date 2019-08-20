#!/bin/bash
git clone https://github.com/thiagotgo90/TINATODO.git
cd TINATODO
git checkout backend-endpoints
mvn install
java -jar target/tinatodo-0.0.1-SNAPSHOT.jar