#!/bin/bash

set -e

APP_NAME="api-erp"
JAR_NAME=$(ls target/*.jar | head -n 1)
DEST_DIR="../../builds/$APP_NAME"
TARGET_DIR="$DEST_DIR/target"
LOG_DIR="$DEST_DIR/logs"

echo "🚀 Iniciando deploy..."

# echo "🔨 Buildando projeto..."
# ./mvnw clean package

echo "Buildando novo Projeto com docker"

docker run --rm \
  -v "$(pwd)":/app \
  -w /app \
  maven:3.9.9-eclipse-temurin-17 \
  mvn clean package -DskipTests
  
echo "📁 Criando diretórios..."
mkdir -p $TARGET_DIR
mkdir -p $LOG_DIR

echo "🧹 Removendo versão antiga..."
rm -f $TARGET_DIR/*.jar

echo "📦 Copiando novo build..."
cp -f $JAR_NAME $TARGET_DIR/

echo "🛑 Parando aplicação antiga..."
PID=$(ps aux | grep "$APP_NAME" | grep -v grep | awk '{print $2}')

if [ -n "$PID" ]; then
  echo "Matando processo $PID"
  kill -9 $PID
else
  echo "Nenhuma aplicação rodando"
fi

echo "▶️ Subindo nova versão..."
nohup java -jar $TARGET_DIR/$(basename $JAR_NAME) > $LOG_DIR/app.log 2>&1 &

echo "✅ Deploy finalizado com sucesso!"