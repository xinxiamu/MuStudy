// chanels
package main

import (
	"fmt"
)

func main() {
	s := []int{7, 2, 8, -9, 4, 0}

	//信道在使用前必须创建
	c := make(chan int)

	//以下示例对切片中的数进行求和，将任务分配给两个 Go 程。 一旦两个 Go 程完成了它们的计算，它就能算出最终的结果。
	go sum(s[:len(s)/2], c)
	go sum(s[len(s)/2:], c)
	x, y := <-c, <-c // 从 c 中接收

	fmt.Println(x, y, x+y)

	fmt.Println("------ 带缓冲的信道  -------")
	//创建缓冲长度为2的信道
	ch := make(chan int, 2)
	//仅当信道的缓冲区填满后，向其发送数据时才会阻塞。当缓冲区为空时，接受方会阻塞
	ch <- 1
	ch <- 2
	//	ch <- 3 //erro
	fmt.Println(<-ch)
	fmt.Println(<-ch)
}

func sum(s []int, c chan int) {
	sum := 0
	for _, v := range s {
		sum += v
	}
	fmt.Println(s, sum)
	c <- sum // 将和送入 c
}
