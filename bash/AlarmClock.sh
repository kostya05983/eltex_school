#!/bin/bash
if [ "$1" = $"add" ]
then
#запрашиваем параметры,время и файл
read -p "Введите путь к файлу" location
read -p "В какой день вас разбудить?(гггг-мм-дд)" data
read -p "В какой час вас разбудить?(чч)" hour
declare -i minutes=''
read -p "Во сколько минут избранного часа вас разбудить?(мм)" minutes
echo "$data $hour:$minutes"
sleep 5
file=$(crontab -l)
echo -e "$file\n$minutes $hour * * * mpg123 $location" |crontab -
fi
if [ "$1" = $"show" ]
then
crontab -l
fi
if [ "$1" = $"erase" ]
then
echo "DISPLAY=:0" |crontab -
fi

if [ "$1" = $"delete" ]
then
read -p "Введите номер записи которую нужно удалить,начиная с 1 " number
file=$(crontab -l)
out=""
for var in $file
do
if [ $number -ne 0 ]
then
out="$out$var"
fi
number=$[$number - 1]
done
echo "$out" |crontab -
fi
file=$(crontab -l)
echo $file

