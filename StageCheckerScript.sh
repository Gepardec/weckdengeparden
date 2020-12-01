#!/bin/bash

echo "Start"
# https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html

### STAGE 1
echo "Stage 1"
mvn clean compile
# shellcheck disable=SC2181
if [[ "$?" -ne 0 ]] ; then
  echo 'WIN-STAGE 1 --> FAILED';
else
  echo "WIN-STAGE 1 --> ACCOMPLISHED"
  mvn clean install
    if [[ "$?" -ne 0 ]] ; then
       echo 'WIN-STAGE 4 --> FAILED';
    else
  echo "BROKEN PROJECT SUCCESSFULLY REPAIRED -- CONGRATULATION"
  fi
fi


### STAGE 2
#echo "Stage 2"
#mvn clean test

### STAGE 3
#echo "Stage 3"
#mvn clean install

echo "StageCheckerScript.sh has been executed by JAVA";bash