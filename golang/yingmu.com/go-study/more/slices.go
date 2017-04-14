package main

import (
	"fmt"
	"strings"
)

var pow = []int{1, 3, 4, 6, 7, 5, 9} //声明并初始化一个切片

//切片并不存储任何数据， 它只是描述了底层数组中的一段。
//更改切片的元素会修改其底层数组中对应的元素。
//与它共享底层数组的切片都会观测到这些修改。
func main() {
	a := [6]int{2, 4, 3, 4, 5, 9}
	var s []int = a[1:4] //类型 []T 表示一个元素类型为 T 的切片。
	fmt.Println(s)
	//操作切片实际操作的是底层数值
	s[0] = 88
	//切片的长度就是它所包含的元素个数。
	//切片的容量是从它的第一个元素开始数，到其底层数组元素末尾的个数。
	fmt.Println(s, len(s), cap(s))
	fmt.Println(a)

	//---------创建一个和上面相同的数组，然后构建一个引用了它的切片：
	q := []int{1, 4, 3, 6}
	//下面几个表示法都是等价的
	//切片下界的默认值为 0 ，上界则是该切片的长度。
	fmt.Println(1, q)
	fmt.Println(2, q[:])
	fmt.Println(3, q[:4])
	fmt.Println(4, q[0:])

	fmt.Println(q[1:3])
	r := []bool{true, false, false, true}
	fmt.Println(r)
	j := []struct {
		i int
		b bool
	}{
		{3, true},
		{6, false}, //注意这里的逗号不能省调
	}
	fmt.Println(j)

	//---nil 切片,切片的零值是 nil 。nil 切片的长度和容量为 0 且没有底层数组。
	var g []int
	fmt.Println(g, len(g), cap(g))
	if g == nil {
		fmt.Println("nil!")
	}

	//---------切片的创建
	fmt.Println("-------- 切片的创建 --------")
	//make 函数会分配一个元素为零值的数组并返回一个引用了它的切片
	k := make([]int, 5)
	fmt.Println(k, len(k), cap(k))
	//要指定它的容量，需向 make 传入第三个参数
	l := make([]int, 0, 5)
	fmt.Println(l, len(l), cap(l))

	//----切片的切片
	fmt.Println("-------- 切片的切片 --------")
	//切片可包含任何类型，甚至包括其它的切片。
	board := [][]string{
		[]string{"_", "_", "_"},
		[]string{"_", "_", "_"},
		[]string{"_", "_", "_", "hello"},
	}
	board[0][0] = "X"
	board[2][2] = "O"
	board[1][2] = "X"
	board[1][0] = "O"
	board[0][2] = "X"
	for i := 0; i < len(board); i++ {
		fmt.Printf("%s\n", strings.Join(board[i], " "))
		//		for j := 0; j < len(board[i]); j++ {
		//			fmt.Println(board[i][j])
		//		}
	}

	//----切片追加
	fmt.Println("-------- 切片元素追加 ---------")
	var z []int //声明空切片
	printSlice(z)
	z = append(z, 0) //append 的第一个参数 s 是一个元素类型为 T 的切片， 其余类型为 T 的值将会追加到该切片的末尾。
	printSlice(z)
	z = append(z, 2, 3, 9)
	printSlice(z)

	//---- 切片遍历
	fmt.Println("------- 遍历切片 --------")
	//当使用 for 循环遍历切片时，每次迭代都会返回两个值。
	//第一个值为当前元素的下标，第二个值为该下标所对应元素的一份副本。
	for i, v := range pow {
		fmt.Printf("2**%d = %d\n", i, v)
	}
	//忽略下标
	for _, value := range pow {
		fmt.Printf("%d\n", value)
	}
	//忽略值
	for i, _ := range pow {
		fmt.Printf("%d\n", i)
	}
	//忽略值
	for i := range pow {
		fmt.Printf("%d\n", i)
	}

}

func printSlice(s []int) {
	fmt.Printf("len=%d cap=%d %v\n", len(s), cap(s), s)
}
