#!/bin/bash

# CONFIGURAÇÕES
SERVER_USER=root
SERVER_IP=cardeneta.com
SERVER_PATH=/root
JAR_NAME=cardeneta-web-0.0.1-SNAPSHOT.jar
LOCAL_JAR=target/$JAR_NAME

echo "📦 Buildando o projeto localmente..."
mvn clean package -DskipTests

# Verificando se o arquivo .jar foi gerado
if [ ! -f "$LOCAL_JAR" ]; then
    echo "❌ Arquivo .jar não encontrado. O build falhou!"
    exit 1
fi

echo "🚀 Enviando o novo .jar para o servidor..."
scp $LOCAL_JAR $SERVER_USER@$SERVER_IP:$SERVER_PATH/$JAR_NAME

echo "🛑 Parando o serviço no servidor..."
# Se já houver uma instância do app rodando, pare-a
ssh $SERVER_USER@$SERVER_IP "pkill -f 'java -jar $SERVER_PATH/$JAR_NAME' || true"

echo "✅ Substituindo o .jar e limpando versões antigas..."
ssh $SERVER_USER@$SERVER_IP "cd $SERVER_PATH && rm -f app.jar && ln -s $JAR_NAME app.jar"

echo "🎯 Iniciando o serviço novamente com nohup..."
# Iniciar o aplicativo em segundo plano com nohup
ssh $SERVER_USER@$SERVER_IP "nohup java -jar $SERVER_PATH/app.jar > $SERVER_PATH/app.log 2>&1 &"

echo "🏁 Deploy finalizado com sucesso!"
