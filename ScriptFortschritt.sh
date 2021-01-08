#!/bin/bash

echo "Start"
# https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html

echo "Kompiliert das Projekt schon?"
mvn clean compile
# shellcheck disable=SC2181
if [[ "$?" -ne 0 ]] ; then
  echo 'Leider kompiliert das Projekt noch nicht :(';
    else
      echo "Super, jetzt kompiliert das Projekt!"
      mvn -Dtest=Base64LengthValidatorTest test
           if [[ "$?" -ne 0 ]] ; then
            echo 'Da dürfte beim Base64LengthValidator etwas nicht passen ...';
              else
              echo "Jetzt passt der Base64LengthValidator. Mal schauen was noch fehlt?"
              mvn -Dtest=Base64ValidatorTest test
            if [[ "$?" -ne 0 ]] ; then
             echo 'Schade, da dürfte beim Base64Validator etwas nicht stimmen ...';
              else
              echo "Super, der Base64Validator funktioniert jetzt!"
              mvn -Dtest=URLValidatorTest test
            if [[ "$?" -ne 0 ]] ; then
              echo 'Hat sich da noch ein Fehler beim URLValidatorTest eingeschlichen?';
                else
                echo "Fast geschafft!"
                mvn -Dtest=AnswerValidatorTest test
            if [[ "$?" -ne 0 ]] ; then
              echo 'Da dürfte nur mehr beim AnswerValidatorTest etwas nicht passen!';
                else
                  echo "Gratuliere! Du hast den Geparden in dir geweckt! Jetzt musst du nur mehr die Bewerbung abschicken!"

  fi
    fi
      fi
          fi
            fi


echo "StageCheckerScript.sh has been executed by JAVA";bash