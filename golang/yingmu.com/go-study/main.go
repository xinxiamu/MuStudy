package main

import (
	"fmt"
	"math"
	"yingmu.com/go-study/constants"
	"yingmu.com/go-study/functions"
)

var i, j int = 1, -2

func main() {
	fmt.Println("------------- 变量声明-begin ---------")
	var java, z, golang, swift = "java", false, "golang", 3
	k := true                     //短变量声明，只能在方法内部声明
	_, e, f := "goods", "哦", true //_（下划线）是个特殊的变量名，任何赋予它的值都会被丢弃
	java, k = "java2", false      //赋值
	e = "赋值给e"
	//声明多行字符串
	m := `hello
      world`
	fmt.Println(m)
	fmt.Println(i, j, java, golang, z, swift, k, e, f, z)
	fmt.Println("------------- 变量声明-end ---------")

	fmt.Println("------ 类型转换-begin --------")
	var q float64 = math.Sqrt(float64(i*j + j*j))
	g := uint(q)
	fmt.Println(q, g, uint(j))
	fmt.Println("------ 类型转换-end --------")

	fmt.Println("------ 常量使用-begin --------")
	fmt.Println(constants.Pi + 1)
	fmt.Println("url", constants.BasePath)
	fmt.Println("------ 常量使用-end --------")

	fmt.Println("--------------- 函数-begin --------------")
	fmt.Println(functions.Add(3, 2))
	fmt.Println(functions.Add1(2, 3, 4))

	a, b := functions.Swap("hello", "world")
	fmt.Println(a, b)

	c, d := functions.Slipt(8)
	fmt.Println(c, d)
	fmt.Println("--------------- 函数-end --------------")
}
