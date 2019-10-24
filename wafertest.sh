#!/bin/bash

zip -j wafers/$1 $1/*.java
cd wafers
java -jar wafer.jar $1.zip $2.java
