#!/bin/bash
read -p "write a location of directories " directory
cd ./$directory
read -p "write an amount of directories,you want to create " amount

while [ $amount -gt 0 ]
do
	read -p "write a name of directory " name
	mkdir $name
	read -p "do you want to create a subfolder y or n " answer
		if [ $answer = "y" ]
		then
			cd ./$name
			read -p "write an amount of directories,you want to create " amountSubFolder
			while [ $amountSubFolder -gt 0 ]
			do
				read -p "write a name of subDirectory " name
				mkdir $name
				read -p "do you want to create a files in directory y or n " answer
					if [ $answer = "y" ]
					then
						cd ./$name
						read -p "write an amount of files,you want to create " amountFiles
							while [ $amountFiles -gt 0 ]
							do
								read -p "write a name of file " name
								>$name
								amountFiles=$[ $amountFiles - 1 ]
							done
						cd ..
					fi
				amountSubFolder=$[ $amountSubFolder - 1 ]
			done
		cd ..	
		fi
	amount=$[ $amount - 1 ]
done
exit 0
