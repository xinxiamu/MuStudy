package main

import (
	"fmt"
	"image/jpeg"
	"log"
	"os"

	"github.com/aaparella/carve"
)

func main() {
	//打开文件
	file, err := os.Open("test.jpg")
	if err != nil {
		fmt.Println(err)
	}
	defer file.Close()

	//创建新的文件
	file1, err := os.Create("test11.jpg")
	if err != nil {
		fmt.Println(err)
	}
	defer file1.Close()

	//解码
	img, err := jpeg.Decode(file)
	if err != nil {
		fmt.Println(err)
	}
	//	jpeg.Encode(file1, img, &jpeg.Options{5}) //编码，但是将图像质量从100改成5
	//图片缩放
	resized, err := carve.ReduceHeight(img, 80)
	if err != nil {
		log.Fatal(err)
	}
	img1, err1 := carve.ReduceWidth(resized, 80)
	if err1 != nil {
		log.Fatal(err1)
	}
	jpeg.Encode(file1, img1, nil)

}
