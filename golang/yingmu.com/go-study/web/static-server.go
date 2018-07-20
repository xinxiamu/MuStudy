//可以做静态文件服务器
//文件路径配置外部化，通过读取ini文件。
package main

import (
	"log"
	"net/http"
	"github.com/unknwon/goconfig"
	"fmt"
)

//配置改成外部化
/*const (
	TemplateDir = "/home/mutian/dev/go/work/src/yingmu.com/go-web/view/"
	//StaticDir   = "/server/www/webapp"
	StaticDir = "/media/mutian/Development/xcjr/requirement_prototype/金信诺保理系统"
	Port      = ":8002"
)*/

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
}


