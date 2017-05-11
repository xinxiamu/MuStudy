#!/bin/bash

#shell流程控制

### 条件
a=10
b=20
if [ $a == $b ]
then
   echo "a 等于 b"
elif [ $a -gt $b ]
then
   echo "a 大于 b"
elif [ $a -lt $b ]
then
   echo "a 小于 b"
else
   echo "没有符合的条件"
fi

#### for循环

# 顺序输出当前列表中的数字
for loop in 1 2 3 4 5
do
  echo "The value is:${loop}"
done

# 顺序输出字符串中的字符
for str in 'This is a string'
do
    echo $str
done

username='mutou'
for (( i = 0; i < 10; i++ )); do
  echo "${username}:$i"
done

#### while
int=1
while ((${int} <=5))
do
  echo $int
  let "int++"
done

#### 无限循环
g=1
# while true
# do
#     echo "无限循环:${g}"
#     let "g++"
# done

# for (( ; ; )) {
#   echo "无限循环:${g}"
#   let "g++"
# }

# while :
# do
#     command
# done

####### until循环
# until循环执行一系列命令直至条件为真时停止。
until [[ ${g} > 5 ]]; do
  echo "until:${g}"
  let "g++"
done

###### 多选择语句
# case
# case 值 in
# 模式1)
#     command1
#     command2
#     ...
#     commandN
#     ;;
# 模式2）
#     command1
#     command2
#     ...
#     commandN
#     ;;
# esac
echo "请输入1-4的数字"
read anum
case ${anum} in
  1)
    echo "你选择了1"
  ;;
  abc)
    echo "你输入了abc"
  ;;
  *)  #相当java中defualt
    echo "没匹配的值"
  ;;
esac

#### 跳出循环
# break命令
while :
do
    echo -n "输入 1 到 5 之间的数字:"
    read aNum
    case $aNum in
        1|2|3|4|5) echo "你输入的数字为 $aNum!"
        ;;
        *) echo "你输入的数字不是 1 到 5 之间的! 游戏结束"
            break
        ;;
    esac
done

# continue
# continue命令与break命令类似，只有一点差别，它不会跳出所有循环，仅仅跳出当前循环。
while :
do
    echo -n "输入 1 到 5 之间的数字: "
    read aNum
    case $aNum in
        1|2|3|4|5) echo "你输入的数字为 $aNum!"
        ;;
        *) echo "你输入的数字不是 1 到 5 之间的!"
            continue
            echo "游戏结束"
        ;;
    esac
done
