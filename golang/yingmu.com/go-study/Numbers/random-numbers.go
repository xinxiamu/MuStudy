// random-numbers
//随机数生产
package main

import (
	"fmt"
	"math/rand"
	"time"
)

var p = fmt.Println

func main() {

	//只第一次执行产生随机数
	p(rand.Intn(1000000), ",")
	p(rand.Intn(1000000))

	fmt.Println(rand.Float64())

	p((rand.Float64() * 5) + 5)
	p((rand.Float64() * 5) + 5)

	//每次执行都产生新的随机数
	s1 := rand.NewSource(time.Now().UnixNano())
	r1 := rand.New(s1)
	fmt.Print(r1.Intn(100), ",")
	fmt.Print(r1.Intn(100))
	fmt.Println()

	fmt.Println("--------------------------")
	//给定种子42，则第一次随机生成随机数后，后面都一样
	s2 := rand.NewSource(42)
	r2 := rand.New(s2)
	fmt.Print(r2.Intn(100), ",")
	fmt.Print(r2.Intn(100))
	fmt.Println()
	s3 := rand.NewSource(42)
	r3 := rand.New(s3)
	fmt.Print(r3.Intn(100), ",")
	fmt.Print(r3.Intn(100))
}
