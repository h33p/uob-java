#!/bin/bash

DIR=$1
FILE=$2

javac -classpath bin/ -d bin $DIR/$FILE.java
java -classpath bin/ com.bham.pij.assignments.$(echo "$FILE" | tr '[:upper:]' '[:lower:]').$FILE
