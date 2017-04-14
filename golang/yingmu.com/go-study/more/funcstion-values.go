package main

import (
	"fmt"
)

//函数也是值。它们可以像其它值一样传递。
//函数值可以用作函数的参数或返回值。
func compute(fn func(float64, float64) float64) float64 {
	return fn(5, 6)
}

//Go 函数可以是一个闭包。闭包是一个函数值，它引用了其函数体之外的变量。
//可以访问并赋予其引用的变量的值，换句话说，该函数被“绑定”在了这些变量上。
//例如，函数 adder 返回一个闭包。每个闭包都被绑定在其各自的 sum 变量上。
func adder() func(int) int {
	sum := 0
	return func(x int) int {
		sum += x
		return sum
	}
}

func main() {
	fn := func(x, y float64) float64 {
		return (x + y) / 2
	}
	fa := func(x, y int) (int, float64) {
		return x, float64(x+y) / 2
	}
	fmt.Println(fn(3, 4))
	a, b := fa(5, 4)
	fmt.Println(a, b)

	fmt.Println(compute(fn))
	//	fmt.Println(compute(fa)) //error

	fmt.Println("---------函数闭包----------")
	m, k := adder(), adder()
	for i := 0; i < 10; i++ {
		fmt.Println(m(i), k(-2*i))
	}

	fmt.Println("----练习，实现斐波那数列-----")
	fib := fibonacci()
	for i := 0; i < 15; i++ {
		//		fmt.Print(fib(i), ",")
		fmt.Printf("%d  ", fib(i))
	}

}

func fibonacci() func(int) int {
	var fn int
	var f0 int = 0
	var f1 int = 1
	return func(x int) int {
		switch {
		case x == 0:
			fn = f0
		case x == 1:
			fn = f1
		default:
			fn = f0 + f1
			f0 = f1
			f1 = fn
		}
		return fn
	}
}
