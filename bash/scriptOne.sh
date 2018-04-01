#!/bin/bash
read -p "write a location of directories " directory
cd ./$directory
read -p "write an amount of directories,you want to create " amountDirectories
read -p "write a name of directories " nameDirectory
read -p "write an amount of subdirectories, you want to create " amountSubDirectories
read -p "write a name of subdirectories " nameSubDirectories
read -p "write an amount of files, you want to create in each subdirectory " amountFiles
read -p "write a name of files " nameFile


i=$amountSubDirectories
j=$amountFiles
while [ $amountDirectories -gt 0 ]
do
mkdir $nameDirectory$amountDirectories
cd ./$nameDirectory$amountDirectories
while [ $i -gt 0 ]
do
mkdir $nameSubDirectories$i
cd ./$nameSubDirectories$i
while [ $j -gt 0 ]
do
>$nameFile$j.txt
j=$[ $j - 1]
done
j=$amountFiles
cd ..
i=$[ $i - 1]
done
i=$amountSubDirectories
cd ..
amountDirectories=$[ $amountDirectories - 1]
done
exit 0
