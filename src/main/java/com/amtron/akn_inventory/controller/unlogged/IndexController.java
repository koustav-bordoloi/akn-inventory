package com.amtron.akn_inventory.controller.unlogged;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping()
    public ModelAndView index() {
        return new ModelAndView("unlogged/index");
    }

}
