package com.amtron.akn_inventory.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("unlogged/login");
        mv.addObject("title", "Login");
        return mv;
    }

    @GetMapping("/login-error")
    public ModelAndView loginError(ModelAndView model, @RequestParam("error") String errorMessage) {
        model.addObject("title", "Login");
        model.addObject("loginError", true);
        model.addObject("loginErrorMessage", errorMessage);
        model.setViewName("unlogged/login");

        return model;
    }

}