#!/bin/bash

echo "Start"
# https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html

echo "Kompiliert es?"
mvn clean compile
# shellcheck disable=SC2181
if [[ "$?" -ne 0 ]] ; then
  echo 'Fortschritt: 0%';
    else
      echo "Fortschritt: 34%"
      mvn -Dtest=Base64LengthValidatorTest test
           if [[ "$?" -ne 0 ]] ; then
            echo 'FAILED';
              else
              echo "Fortschritt: 50%"
              mvn -Dtest=Base64ValidatorTest test
            if [[ "$?" -ne 0 ]] ; then
             echo 'FAILED';
              else
              echo "Fortschritt: 66%"
              mvn -Dtest=URLValidatorTest test
            if [[ "$?" -ne 0 ]] ; then
              echo 'FAILED';
                else
                echo "Fortschritt: 84%"
                mvn -Dtest=AnswerValidatorTest test
            if [[ "$?" -ne 0 ]] ; then
              echo 'FAILED';
                else
                  echo "100% Bestanden!"

  fi
    fi
      fi
          fi
            fi


echo "StageCheckerScript.sh has been executed by JAVA";bash