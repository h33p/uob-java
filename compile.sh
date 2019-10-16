#!/bin/bash

DIR=$1
FILE=$2

if [[ ! -d bin ]]; then
	mkdir bin
fi

javac -classpath bin/ -d bin $DIR/$FILE.java
