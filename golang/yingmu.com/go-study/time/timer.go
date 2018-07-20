//简单定时器,心跳程序
//定时执行任务
//解析ini文件
//执行脚本命令
package main

import (
	"github.com/unknwon/goconfig" //解析ini文件
	"fmt"
	"time"
	"os/exec"
	"bytes"
)

var config *goconfig.ConfigFile

func init() {
	//放在可执行文件timer的同一个目录下
	path := "./config.ini"
	conf, err := goconfig.LoadConfigFile(path)
	if err != nil {
		fmt.Println(err)
	}
	config = conf
}

func main() {
	t := time.NewTicker(1 * time.Second) //1秒钟执行一次
	for {
		select {
		case <-t.C:
			//fmt.Println(">>>:" + time.Now().String())
			run()
 		}
	}
}

func run()  {
	startTimestamp, _ := config.Int64("time", "startTimestamp")
	loopSeconds, _ := config.Int64("time", "loopSeconds")
	unix := time.Now().Unix()
	if startTimestamp <= unix && (unix-startTimestamp)%loopSeconds == 0 {
		cmd()
	}
}

//执行脚本
func cmd()  {
	scriptBinPath, _ := config.GetValue("cmd", "scriptBinPath")
	filePath, _ := config.GetValue("cmd", "filePath")
	params, _ := config.GetValue("cmd", "params")
	cmd := exec.Command(scriptBinPath, filePath, params)
	var out bytes.Buffer
	cmd.Stdout = &out
	err := cmd.Start()
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println("执行中……")
	err = cmd.Wait()
	if err != nil {
		fmt.Printf("执行错误: %v", err)
	}
	fmt.Println(out.String())
}
