package functions

func Add(x int, y int) int {
	return x + y
}

//当连续两个或多个函数的已命名形参类型相同时，除最后一个类型以外，其它都可以省略。
func Add1(x, y int, z int) int {
	return x + y + z
}

//函数可以返回任意数量的返回值。
func Swap(x, y string) (string, string) {
	return y, x
}

//Go 的返回值可被命名，它们会被视作定义在函数顶部的变量。
//返回值的名称应当具有一定的意义，它可以作为文档使用。
//没有参数的 return 语句返回已命名的返回值。也就是`直接`返回。
//直接返回语句应当仅用在下面这样的短函数中。在长的函数中它们会影响代码的可读性。
func Slipt(sum int) (x, y int) {
	x = sum * 10
	y = x - sum
	return
}
