package main

import (
	"fmt"
)

type person struct {
	name, sex string
	age       int
}

var m map[string]person

func main() {
	fmt.Println("----------- 创建映射 ------------")
	fmt.Println(m)
	m = make(map[string]person)
	m["mutian"] = person{
		"zhangmutian", "男", 18,
	}
	fmt.Println(m["mutian"].name, m["mutian"])

	fmt.Println("-----------映射的key必须是string类型 -----")
	mp := make(map[string]string)
	mp["a"] = "hello"
	mp["b"] = "world"
	fmt.Println(mp, mp["a"], mp["b"])

	fmt.Println("-----------映射的文法 -----")
	//映射的文法与结构体相似，不过必须有键名。
	m = map[string]person{
		"mutou":  {"张木天", "男", 18},
		"jiazhu": {"黄莹莹", "女", 17},
		"baobao": {"张爱莹", "女", 2},
	}
	fmt.Println(m, m["mutou"].name, m["jiazhu"].name)
	delete(m, "mutou") //删除元素
	fmt.Println(m["mutou"])
	//通过双赋值检测某个键是否存在：
	//若 key 在 m 中， ok 为 true ；否则， ok 为 false 。
	//
	value, ok := m["mutou"]
	fmt.Println(value, ok)

}
