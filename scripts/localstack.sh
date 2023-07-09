#!/bin/bash

REGION=us-east-1

function fail() {
    echo "$2"
    exit "$1"
}

echo
echo "🚀 Iniciando recursos do localstack ---"

echo "🟢 Executando: aws sqs create-queue:"
nohup aws --endpoint="http://localhost:4566" sqs create-queue \
    --queue-name=order-queue \
    --region ${REGION} \

# shellcheck disable=SC2181
[ $? == 0 ] || fail 1 "🔴 Failed: AWS / queue / create-queue"

echo
echo "✨  Concluído --------------------------"