package main

import (
	"fmt"
)

type Vertex struct {
	X int
	Y int
}

type Person struct {
	name string
	age  int
	sex  string
}

var (
	//结构体初始化
	v1 = Vertex{X: 8}  //X=8,Y=0
	v2 = Vertex{}      //X=0,Y=0
	q  = &Vertex{3, 4} //指针q指向该结构体
)

func main() {
	v := Vertex{3, 7}
	fmt.Println(v.X, v.Y, v)
	v.X = 99
	fmt.Println(v.X)

	p := &v //定义指针指向v
	p.Y = 33
	fmt.Println(p.X, v.X, v.Y, p.Y)

	fmt.Println(v1, q, v2, q.X)

	person1 := Person{name: "张木天", age: 101}
	fmt.Println(person1.name, person1.age, person1.sex)

	//初始化结构体
	var person2 Person
	person2.name = "李四"
	person2.age = 66
	fmt.Println(person2.name, person2.age)

	h := new(Person) //通过new函数分配一个指针，此处h的类型为*person
	h.name = "小鱼儿"
	h.age = 18
	fmt.Println(h.name, h.age, *h, &h, h)
}
