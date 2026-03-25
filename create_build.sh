#!/bin/bash
echo "init build"
mvn clean package
echo 'remove build old'
rm ../../builds/order/api/target/*.jar
echo 'create build new'
cp -rf target/api-0.0.1-SNAPSHOT.jar ../../builds/order/api/target/api-0.0.1-SNAPSHOT.jar
echo 'end'