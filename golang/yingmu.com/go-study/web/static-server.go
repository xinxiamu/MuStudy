package main

import (
	"log"
	"net/http"
)

const (
	TemplateDir = "/home/mutian/dev/go/work/src/yingmu.com/go-web/view/"
	StaticDir   = "/server/static/wang/"
	Port        = ":80"
)

func main() {
	h := http.FileServer(http.Dir(StaticDir))
	//静态文件服务器
	//http.Handle("/static/", http.StripPrefix("/static/", h))
	http.Handle("/", http.StripPrefix("/", h))

	err := http.ListenAndServe(Port, nil)
	if err != nil {
		log.Fatal("ListenAndServe: ", err)
	}
}
