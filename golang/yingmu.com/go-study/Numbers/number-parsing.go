// number-parsing
//字符串转数字
package main

import (
	"fmt"
	"strconv"
)

func main() {
	//this 64 tells how many bits of precision to parse.
	//第二个参数表示精度
	f64, _ := strconv.ParseFloat("1.234", 64)
	f32, _ := strconv.ParseFloat("1.234", 32)
	fmt.Println(f64, ",", f32)

	//For ParseInt, the 0 means infer the base from the string. 64 requires that the result fit in 64 bits.
	//0表示从字符串转换
	i, _ := strconv.ParseInt("123", 0, 64)
	j, _ := strconv.ParseInt("-123", 0, 64)
	fmt.Println(i, j)

	//十六进制字符串转整形
	d, _ := strconv.ParseInt("0x1c8", 0, 64)
	fmt.Println(d)

	//无符号的整形转换
	u, _ := strconv.ParseUint("-789", 0, 64)
	u1, _ := strconv.ParseUint("789", 0, 64)
	fmt.Println(u, u1)

	//Atoi is a convenience function for basic base-10 int parsing.
	//下面两个方法等价，返回的int类型
	k, _ := strconv.Atoi("135")
	q, _ := strconv.ParseInt("135", 10, 0)
	fmt.Println(k, q)

	_, e := strconv.Atoi("wat")
	fmt.Println(e)
}
