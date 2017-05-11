#!/bin/bash
# shell传递参数实例
echo "执行的文件名：$0"
echo "第一个参数：$1"
echo "第二个参数：$2"
td=$3
echo "第三个参数：${td}"
echo "参数个数：$#"
echo "参数作为字符串显示：$*"
