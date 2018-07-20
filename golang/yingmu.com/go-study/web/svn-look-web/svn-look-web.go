//文件服务器
//配置文件外部化
//每隔一段时间执行脚本，checkout svn中内容
package main

import (
	"os/exec"
	"bytes"
	"fmt"
	"time"
	"net/http"
	"log"
	"github.com/unknwon/goconfig"
)

var config *goconfig.ConfigFile

func init() {
	fmt.Println()
	path := "./config.ini"
	conf, err := goconfig.LoadConfigFile(path)
	if err != nil {
		fmt.Println(err)
	}
	config = conf
}

func main() {
	staticFilePath,_ := config.GetValue("basic","filePath")
	port,_ := config.GetValue("basic","port")

	h := http.FileServer(http.Dir(staticFilePath))
	//静态文件服务器
	//http.Handle("/static/", http.StripPrefix("/static/", h))
	http.Handle("/", http.StripPrefix("/", h))

	err := http.ListenAndServe(port, nil)
	if err != nil {
		log.Fatal("ListenAndServe: ", err)
	}

	//每隔时间执行
	//timeDiff,_ := config.Int("timer","timeDiff")
	t := time.NewTicker(1 * time.Second) //1秒钟执行一次
	for {
		select {
		case <-t.C:
			fmt.Println(">>>:" + time.Now().String())
			//cmd()
		}
	}

}

func cmd()  {
	scriptBinPath, _ := config.GetValue("cmd", "scriptBinPath")
	cmd := exec.Command(scriptBinPath)
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
	fmt.Println("执行完成!!!!!")
}


