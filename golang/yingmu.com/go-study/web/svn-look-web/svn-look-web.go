//文件服务器
//配置文件外部化
//每隔一段时间执行脚本，checkout svn中内容
package main

import (
	"os/exec"
	"bytes"
	"fmt"
	"github.com/unknwon/goconfig"
	"time"
	"net/http"
	"log"
)

var config *goconfig.ConfigFile

func init() {
	fmt.Println()
	//path := "/home/mutian/dev/go/work/src/yingmu.com/go-study/web/svn-look-web/config.ini"
	path := "./config.ini"
	conf, err := goconfig.LoadConfigFile(path)
	if err != nil {
		fmt.Println(err)
	}
	config = conf
}

func init() {
	go svnupdate()
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

}

//心跳执行
func svnupdate()  {
	//timeDiff,_ := config.Int("timer","timeDiff")
	t := time.NewTicker(10 * time.Minute) //10分钟
	for {
		select {
		case <-t.C:
			fmt.Println(">>>执行时间:" + time.Now().String())
			cmd()
		}
	}

}

func cmd()  {
	scriptBinPath, _ := config.GetValue("cmd", "scriptBinPath")
	cmd := exec.Command(scriptBinPath)
	//cmd := exec.Command("/home/mutian/dev/go/work/src/yingmu.com/go-study/web/svn-look-web/svn-update.sh")
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


