#!/bin/bash

DIR=$1
FILE=$2

./compile.sh $DIR $FILE
java -classpath bin/ com.bham.pij.assignments.$(echo "$FILE" | tr '[:upper:]' '[:lower:]').$FILE
