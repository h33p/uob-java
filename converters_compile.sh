#!/bin/sh

./compile.sh converters IConverter converters
./compile.sh converters InvalidFormatException converters
./compile.sh converters GenericBaseConverter converters
./compile.sh converters Converter converters
