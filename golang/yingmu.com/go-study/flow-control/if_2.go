package main

import "os"

func main() {

	println(os.Args)
	println(len(os.Args))
	for e := range os.Args {
		println(e)
	}

	//字符串比较
	name := "zmt"
	power := 0
	if name == "zmt" {
		println("name is zmt")
	}

	if (name == "Goku" && power > 9000) || (name == "zmtf" && power < 4000) {
		print("super Saiyan")
	}
}
