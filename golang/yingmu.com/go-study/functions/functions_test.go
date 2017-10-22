package functions


import (
	"testing"
)

func TestAdd(t *testing.T) {

}

func TestSwap(t *testing.T) {
	a, b := Swap("你好", "张木天")
	t.Log("第一个测试通过了" + a + b)
}
