#!/bin/bash

DIR=$1
FILE=$2
PACKAGE=$3

if [ -z $PACKAGE ]; then
	PACKAGE=$(echo "$FILE" | tr '[:upper:]' '[:lower:]')
fi

./compile.sh $DIR $FILE
java -classpath bin/ com.bham.pij.assignments.$PACKAGE.$FILE
