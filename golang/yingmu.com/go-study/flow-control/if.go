package main

import (
	"fmt"
	"math"
)

//同 for 一样， if 语句可以在条件表达式前执行一个简单的语句。
//该语句声明的变量作用域仅在 if 之内。
func pow(x, n, lim float64) float64 {
	if v := math.Pow(x, n); v < lim {
		return v
	}
	return lim
}

func pow2(x, n, lim float64) float64 {
	if v := math.Pow(x, n); v < lim {
		return v
	} else {
		fmt.Printf("%g >= %g\n", v, lim)
	}
	// 这里开始就不能使用 v 了
	return lim
}

//用牛顿下山法求开方
func Sqrt(x float64) float64 {
	z := 1.0
	for i := 0; i < 10; i++ {
		z = z - (z*z-x)/(2*z)
		fmt.Println("z=", z)
	}
	return z
}

func main() {
	fmt.Println(
		pow(3, 2, 10),
		pow(3, 3, 20),
		pow2(3, 3, 20),
	)

	fmt.Println(Sqrt(9))
	fmt.Println(math.Sqrt(9))
}
