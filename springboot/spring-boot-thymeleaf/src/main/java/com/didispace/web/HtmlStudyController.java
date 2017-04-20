package com.didispace.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/html")
public class HtmlStudyController {

    @RequestMapping("/form")
    public String form(Model model) {
        return "demo/form";
    }

}