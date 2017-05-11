#!/bin/bash

####Shell输入输出重定向

##输出重定向
# 命令	说明
# command > file	将输出重定向到 file。
# command < file	将输入重定向到 file。
# command >> file	将输出以追加的方式重定向到 file。
# n > file	将文件描述符为 n 的文件重定向到 file。
# n >> file	将文件描述符为 n 的文件以追加的方式重定向到 file。
# n >& m	将输出文件 m 和 n 合并。
# n <& m	将输入文件 m 和 n 合并。
# << tag	将开始标记 tag 和结束标记 tag 之间的内容作为输入。

# who > ouputRedict.txt
echo "输出重定向到文本" > ouputRedict.txt #会覆盖文本
echo "追加输出" >> ouputRedict.txt

##输入重定向
