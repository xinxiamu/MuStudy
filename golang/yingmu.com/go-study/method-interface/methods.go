package main

import (
	"fmt"
	"math"
	//	"strings"
)

type Vertex struct {
	X, Y float64
}

//Go 没有类。不过你可以为结构体类型定义方法。
//方法拥有一个名为 v ，类型为 Vertex 的接收者。
//方法就是一类带特殊的 接收者 参数的函数。
//记住：方法只是个带接收者参数的函数。
//对Vertex副本操作
func (v Vertex) Abs() float64 {
	return math.Sqrt(v.X*v.X + v.Y*v.Y)
}

//为结构体定义函数
func (v Vertex) demo(k string) (float64, string) {
	return v.Abs(), k + "hello"
}

//指针接收者
//这意味着对于某类型 T ，接收者的类型可以用 *T 的文法。 （此外， T 不能是像 *int 这样的指针。
//若使用值接收者，那么 Scale 方法会对原始 Vertex 值的副本进行操作
func (v *Vertex) Scale(f float64) {
	v.X = v.X * f
	v.Y = v.Y * f

}

//为非结构体类型声明方法
type MyFloat float64

func (mf MyFloat) A() float64 {
	if mf < 0 {
		return float64(-mf)
	}
	return float64(mf)
}

//就是接收者的类型定义和方法声明必须在同一包内；不能为内建类型声明方法。

//选择值或指针作为接收者
/*
使用指针接收者的原因有二：

首先，方法能够修改其接收者指向的值。

其次，这样可以避免在每次调用方法时复制该值。若值的类型为大型结构体时，这样做会更加高效。

在本例中， Scale 和 Abs 接收者的类型为 *Vertex ，即便 Abs 并不需要修改其接收者。

通常来说，所有给定类型的方法都应该有值或指针接收者，但并不应该二者混用。 （我们会在接下来几页中明白为什么。）
*/

func main() {
	v := Vertex{3, 4}
	fmt.Println(v.Abs())
	v.X = 2
	d, _ := v.demo("worlds")
	_, h := v.demo("ym,")
	fmt.Println(d, h)

	fmt.Println("--- 为非结构体定义方法 ---")
	mf := MyFloat(-1.00)
	fmt.Println(mf.A())

	fmt.Println("--- 指针接收者 ---")
	//由于 Scale 方法有一个指针接收者，为方便起见，Go 会将语句 q.Scale(5) 解释为 (&q).Scale(5)
	q := Vertex{2, 5}
	q.Scale(5)
	fmt.Println(q.X)

	//方法调用 n.demo("aaa")会被解释为 (*n).demo("aaa") 。
	n := &Vertex{1, 2}
	j, _ := n.demo("aaa")
	fmt.Println(j)
}
