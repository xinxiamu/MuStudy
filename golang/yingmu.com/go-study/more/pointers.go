// pointer
package main

import (
	"fmt"
)

var q *string //定义一个指向string类型的指针

//safsaf
func main() {
	i, j, k := 3, "hello world", 9
	p := &i         //定义指针p，指向i
	fmt.Println(*p) //通过指针读取i的值
	*p = 23         //通过指针赋值给i，改变i的值
	fmt.Println(i)

	p = &k      //p指针重新指向k
	*p = *p / 3 //指针操作
	fmt.Println(*p, k)

	q = &j //q指针指向j
	fmt.Println(*q)

	//不同类型指针不可以操作
	//	p = q
	//	fmt.Println(p)
}
