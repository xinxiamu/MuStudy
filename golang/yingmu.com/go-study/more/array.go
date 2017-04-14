package main

import (
	"fmt"
)

func main() {
	//类型 [n]T 表示拥有 n 个 T 类型的值的数组。
	//数组的长度是其类型的一部分，因此数组不能改变大小。
	var a [2]int
	a[0] = 3
	a[1] = 9
	fmt.Println(a, a[0], a[1])

	b := [3]string{"zhang", "mu", "tian"}
	fmt.Println(b)
}
