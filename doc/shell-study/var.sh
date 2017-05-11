!/bin/bash
your_name="mutou.com"
echo ${your_name}
#重新定义变量
your_name="linux"
echo ${your_name}

#只读变量
myurl="ymu.cn"
readonly myurl
myurl="ymu.com"


#删除变量
unset your_name
echo ${your_name}

#拼接字符串
name="mutou"
greeting="hello,${name}!"
greeting1="你好,${name}！"
echo ${greeting},${greeting1}

#字符串长度
string="mutou is a boy"
echo ${#string}
#提取子字符串
echo ${string:2:5}
#查找子字符串，“i或s”的位置,注意是反引号
echo `expr index "${string}" is`

#数组
array_name=(a b c d)
echo ${array_name[2]}
echo ${array_name[@]}	#读取所有
l=${#array_name[@]} #数组长度
echo ${l}

