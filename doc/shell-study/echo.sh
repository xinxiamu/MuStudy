#!/bin/bash
# 显示普通字符串
echo "It is a test"
echo It is a test

# 显示转义字符
echo ""It is a test""

# 显示变量
read name
echo "${name} It is a test"

# 显示换行
echo -e "OK! n" #-e 开启转义
echo  "It is a test"

# 显示不换行
echo -e "OK! c" #c不换行
echo "It is a test"

# 显示结果定向文件
echo "It is a test" > echo.txt

# 原样输出字符串，不进行转义或取变量(用单引号)
echo '$name"'

# 显示命令执行结果
echo `date`
