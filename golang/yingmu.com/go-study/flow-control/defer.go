package main

import (
	"fmt"
	"yingmu.com/go-study/functions"
)

func main() {
	//defer 语句会将函数推迟到外层函数返回之后执行。
	//推迟调用的函数其参数会立即求值，但直到外层函数返回前该函数都不会被调用。
	defer fmt.Println(functions.Add(3, 7))
	defer fmt.Println(99)
	fmt.Println("----测试defer使用：")

	//推迟的函数调用会被压入一个栈中。 当外层函数返回时，被推迟的函数会按照后进先出的顺序调用。
	fmt.Println("counting")
	for i := 0; i < 10; i++ {
		defer fmt.Println(i)
	}
	fmt.Println("done")
}
