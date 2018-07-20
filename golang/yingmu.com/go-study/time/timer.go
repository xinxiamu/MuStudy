//简单定时器
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
	//放在
	path := "./config.ini"
	conf, err := goconfig.LoadConfigFile(path)
	if err != nil {
		fmt.Println(err)
	}
	config = conf
}

func main() {
	t := time.NewTicker(1 * time.Second)
	for {
		select {
		case <-t.C:
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
	fmt.Println("Waiting for command to finish...")
	err = cmd.Wait()
	if err != nil {
		fmt.Printf("Command finished with error: %v", err)
	}
	fmt.Println(out.String())
}
