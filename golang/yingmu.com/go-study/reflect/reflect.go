// reflect
package main

import (
	"fmt"
	"reflect"
)

func main() {
	var x float64 = 3.43
	v := reflect.ValueOf(x)
	fmt.Println("type:", v.Type())
	fmt.Println("kind is float64:", v.Kind() == reflect.Float64)
	fmt.Println("value:", v.Float())

	fmt.Println("----------修改值")
	p := reflect.ValueOf(&x)
	g := p.Elem()
	g.SetFloat(7.1)
	fmt.Println(x)
}
