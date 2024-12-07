package com.amtron.akn_inventory.controller.unlogged;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for handling errors.
 */
@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public ModelAndView handleError(ModelAndView model, @RequestParam(required = false) String actualErrorMsg) {

        model.setViewName("unlogged/error");
        model.addObject("errorMsg", actualErrorMsg);

        return model;
    }

}
