// range-and-close
package main

import (
	"fmt"
)

//注意： 只有发送者才能关闭信道，而接收者不能。向一个已经关闭的信道发送数据会引发程序恐慌（panic）。
//还要注意： 信道与文件不同，通常情况下无需关闭它们。只有在必须告诉接收者不再有值需要发送的时候才有必要关闭，例如终止一个 range 循环。
func fibonacci(n int, c chan int) {
	x, y := 0, 1
	for i := 0; i < n; i++ {
		c <- x
		x, y = y, x+y
	}
	close(c) //关闭信道
}

func main() {
	c := make(chan int, 10)
	fmt.Println("----a=", cap(c))
	go fibonacci(cap(c), c)
	fmt.Println("----b=", cap(c))
	//循环 `for i := range c` 会不断从信道接收值，直到它被关闭。
	for i := range c {
		fmt.Println(i)
	}
	fmt.Println("----c=", cap(c))
}
