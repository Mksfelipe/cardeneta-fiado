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
# Rodando o comando para matar o processo Java existente
ssh $SERVER_USER@$SERVER_IP << EOF
  PID=\$(ps aux | grep "java -jar $SERVER_PATH/app.jar" | grep -v grep | awk '{print \$2}')
  if [ -n "\$PID" ]; then
    echo "Terminando o processo com PID \$PID..."
    kill -9 \$PID
    echo "Processo com PID \$PID foi terminado."
  else
    echo "Processo não encontrado. Nenhuma aplicação Java rodando com '$SERVER_PATH/$JAR_NAME'."
  fi
EOF

echo "✅ Substituindo o .jar e limpando versões antigas..."
ssh $SERVER_USER@$SERVER_IP << EOF
  if [ -f "$SERVER_PATH/app.jar" ]; then
    rm -f $SERVER_PATH/app.jar
  fi
  ln -s $SERVER_PATH/$JAR_NAME $SERVER_PATH/app.jar
EOF

echo "🎯 Iniciando o serviço novamente com nohup..."
# Iniciar o aplicativo em segundo plano com nohup
ssh $SERVER_USER@$SERVER_IP "nohup java -jar $SERVER_PATH/app.jar > $SERVER_PATH/app.log 2>&1 &"

echo "🏁 Deploy finalizado com sucesso!"