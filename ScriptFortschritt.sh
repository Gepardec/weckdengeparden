#!/bin/bash

echo -e "Start\n"
# https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html

tput smul; tput setaf 3; echo -e "\nKompiliert das Projekt schon?\n"
mvn clean compile
# shellcheck disable=SC2181
if [[ "$?" -ne 0 ]] ; then
  tput smul; tput setaf 3; echo -e "\nLeider kompiliert das Projekt noch nicht :(\n";
    else
       tput smul; tput setaf 3; echo -e "\nSuper, jetzt kompiliert das Projekt!\n"
      mvn -Dtest=Base64LengthValidatorTest test
           if [[ "$?" -ne 0 ]] ; then
            tput smul; tput setaf 3; echo -e '\nDa dürfte beim Base64LengthValidator etwas nicht passen ...\n';
              else
              tput smul; tput setaf 3; echo -e "\nJetzt passt der Base64LengthValidator. Mal schauen was noch fehlt?\n"
              mvn -Dtest=Base64ValidatorTest test
            if [[ "$?" -ne 0 ]] ; then
             tput smul; tput setaf 3; echo -e '\nSchade, da dürfte beim Base64Validator etwas nicht stimmen ...\n';
              else
              tput smul; tput setaf 3; echo -e "\nSuper, der Base64Validator funktioniert jetzt!\n"
              mvn -Dtest=URLValidatorTest test
            if [[ "$?" -ne 0 ]] ; then
              tput smul; tput setaf 3; echo -e '\nHat sich da noch ein Fehler beim URLValidatorTest eingeschlichen?\n';
                else
                tput smul; tput setaf 3; echo -e "\nFast geschafft!\n"
                mvn -Dtest=AnswerValidatorTest test
            if [[ "$?" -ne 0 ]] ; then
              tput smul; tput setaf 3; echo -e '\nDa dürfte nur mehr beim AnswerValidatorTest etwas nicht passen!\n';
                else
                  tput smul; tput setaf 3; echo -e "\nGratuliere! Du hast den Geparden in dir geweckt! Jetzt musst du nur mehr die Bewerbung abschicken!\n"

  fi
    fi
      fi
          fi
            fi


echo "StageCheckerScript.sh has been executed by JAVA";bash