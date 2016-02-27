package dev.codder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Daniil on 2/26/2016.
 */
@Controller
@RequestMapping("/web")
public class ViewController {

    @RequestMapping(value = "/welcome/",method = RequestMethod.GET)
    public ModelAndView test(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }
}
