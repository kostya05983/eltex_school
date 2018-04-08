#!/bin/bash
if [ "$1" = $"add" ]
then
read -p "Введите путь к файлу " location
read -p "В какой час вас разбудить?(чч) " hour
declare -i minutes=''
read -p "Во сколько минут избранного часа вас разбудить?(мм) " minutes
echo "$hour:$minutes"
sleep 4
echo -e "$(crontab -l)\n#alarmClock\n$minutes $hour 1-31 1-12 1-7 mpg123 $location" |crontab -
echo "$minutes $hour 1-31 1-12 1-7 mpg123 $location" >> tasks.txt 
fi

if [ "$1" = $"show" ]
then
cat tasks.txt
fi

if [ "$1" = $"erase" ]
then
echo "DISPLAY=:0" |crontab -
echo "" > tasks.txt
fi

if [ "$1" = $"delete" ]
then
read -p "Введите номер записи которую нужно удалить,начиная с 1 " number
file=$(crontab -l)
flag=8
out=""
tmp=$[$number - 1]
for var in $file
do
if [ "$var" = $"#alarmClock" ]
then
number=$[$number - 1]
fi
if [ $number -eq 0 ]
then
if [ $flag -gt 0 ]
then
flag=$[$flag - 1]
continue
fi
fi
out="$out $var"
done
echo "$out" |crontab -

flag=7
out=""
for var in $(cat tasks.txt)
do
if [ $tmp -eq 0 ]
then
if [ $flag -gt 0 ]
then
flag=$[$flag -1]
continue
fi
fi
tmp=$[$tmp - 1]
echo $var
out="$out $var"
done
echo "$out" >tasks.txt
fi

if [ "$1" = $"edit" ]
then
read -p "Введите номер записи который хотите отредактировать " number
read -p "В какой час вас разбудить?(чч) " hours
declare -i minutes=''
read -p "Во сколько минут избранного часа вас разбудить?(мм) " minutes
flag=2
tmp=$[$number - 1]
for var in $(crontab -l)
do
if [ "$var" = $"#alarmClock" ]
then
number=$[$number - 1]
out="$out #alarmClock"
continue
fi
if [ $number -eq 0 ]
then
if [ $flag -eq 2 ]
then
out="$out $minutes"
flag=$[$flag - 1]
continue
fi
if [ $flag -eq 1 ]
then
out="$out $hours"
flag=$[$flag - 1]
continue
fi
fi
out="$out $var"
done
echo "$out" |crontab -

flag=2
out=""
for var in $(cat tasks.txt)
do
if [ $tmp -eq 0 ]
then
if [ $flag -eq 2 ]
then
out="$out $minutes"
flag=$[$flag - 1]
continue
fi
if [ $flag -eq 1 ]
then
out="$out $hours"
flag=$[$flag - 1]
continue
fi
fi
tmp=$[$tmp - 1]
out="$out $var"
done
echo "$out" >tasks.txt
fi

if [ "$1" = "help" ]
then
echo "add - добавить новую запись будильника
delete - удалить запись будильника
edit - отредактировать запись будильника
show - отобразить записи будильника"
fi






