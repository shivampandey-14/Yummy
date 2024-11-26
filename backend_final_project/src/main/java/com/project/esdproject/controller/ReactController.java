package com.project.esdproject.controller;

import org.springframework.stereotype.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactController {
    // Matches all paths not starting with /api/v1/customer or any file extension like .js/.css
    @RequestMapping(value = {"/{path:[^\\.]*}"})
    public String forward() {
        return "forward:/index.html"; // React's index.html
    }
}