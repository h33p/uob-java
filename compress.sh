#!/bin/bash

rm -rf ignored/compress/$1

if [[ ! -d ignored/compress ]]; then
	mkdir ignored/compress/
fi

mkdir ignored/compress/$1

for f in $1/*.java; do
	cat $f | sed 's/\t/    /g' > ignored/compress/$f
done

zip -j ignored/compress/$1 ignored/compress/$1/*.java
