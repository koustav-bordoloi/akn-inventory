package com.amtron.akn_inventory.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/default-dashboard")
public class AdminController {

    @GetMapping
    public ModelAndView adminDashboard() {
        ModelAndView mv = new ModelAndView("admin/dashboard");
        return mv;
    }

}
