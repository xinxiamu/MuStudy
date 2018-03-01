package main

import (
	"os"
	"text/template"
)

type Person struct {
	Name                string
	nonExportedAgeField string //because it doesn't start with a capital letter
}

func main() {
	t := template.New("hello view")    //create a new view with some name
	t, _ = t.Parse("hello {{.Name}}!") //parse some content and generate a view, which is an internal representation

	p := Person{Name: "Mary"} //define an instance with required field

	t.Execute(os.Stdout, p) //merge view ‘t’ with content of ‘p’
}
