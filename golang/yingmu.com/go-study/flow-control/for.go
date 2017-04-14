package main

import (
	"fmt"
	"runtime"
	"time"
)

func run(i, n int, ch chan int) {
	count := 0
	for i := i; i < n; i++ {
		count = count + i
	}
	ch <- count

}

func main() {
	t1 := time.Now()
	NCPU := runtime.NumCPU()
	runtime.GOMAXPROCS(NCPU)
	chs := make([]chan int, NCPU)
	for i := 0; i < NCPU; i++ {
		chs[i] = make(chan int)
		n := 9000000000 / NCPU
		go run(i*n, (i+1)*n, chs[i])
	}
	count := 0
	for i := 0; i < NCPU; i++ {
		t := <-chs[i]
		count = count + t
	}
	t2 := time.Now()
	fmt.Printf("cpu num:%d,cost:%d,count:%d\n", NCPU, t2.Sub(t1), count)

	//累加
	t3 := time.Now()
	sum := 0
	for i := 0; i < 9000000000; i++ {
		sum = sum + i
		//		sum += i
	}
	t4 := time.Now()
	fmt.Printf("cost:%d,count:%d\n", t4.Sub(t3), sum)

	//初始化语句和后置语句是可选的。
	summu := 1
	for summu < 15 {
		summu += summu
	}
	fmt.Println(summu)

	//死循环
	//	for true {
	//		fmt.Println(1)
	//	}

	//	i := 0
	//	for {
	//		i++
	//		fmt.Println(i)
	//	}
}
