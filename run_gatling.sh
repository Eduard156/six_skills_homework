#!/bin/bash

echo "Очистка проекта и компиляция..."
sbt clean compile

echo "Запуск Gatling симуляции..."
sbt "gatling:testOnly perf.load.Debug"

REPORT_DIR=$(ls -td target/gatling/* | head -1)

echo "Gatling отчет сохранен в: $REPORT_DIR"