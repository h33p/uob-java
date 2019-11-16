#!/bin/bash

DIR="$1/"
ASSIGNMENT=$2
ASSIGNMENT_LC=$(echo "$ASSIGNMENT" | tr '[:upper:]' '[:lower:]')

if [[ ! -f $DIR/$ASSIGNMENT.java ]]; then

	echo "package com.bham.pij.assignments.$ASSIGNMENT_LC;

public class $ASSIGNMENT {
	public static void main(String[] args) {
		$ASSIGNMENT instance = new $ASSIGNMENT();
	}
}
" >> $DIR/$ASSIGNMENT.java

else
	echo "Class exists!"
fi
