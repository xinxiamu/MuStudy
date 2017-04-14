// goruntines
package main

import (
	"fmt"
	"time"
)

func say(t string, s string) {
	for i := 0; i < 5; i++ {
		time.Sleep(100 * time.Millisecond)
		fmt.Println(t, s)
	}
}

func main() {
	//启动一个新的 Go 程并执行
	//运行时管理的轻量级线程。
	go say("go=", "world")
	say("n=", "hello")
}
