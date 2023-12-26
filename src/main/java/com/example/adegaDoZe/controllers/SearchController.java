package com.example.adegaDoZe.controllers;

import com.example.adegaDoZe.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class SearchController {
    @Autowired
    private StockRepository sr;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView openIndex(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView searchIndex(@RequestParam("search") String search, @RequestParam("name") String name){
        ModelAndView mv = new ModelAndView("index");
        String message = "Search results for " + search;

        if(name.equals("productName")){
            mv.addObject("products", sr.findByNames(search));
        }else{
            mv.addObject("products", sr.findByNames(search));
        }

        mv.addObject("message", message);
        return mv;
    }
}
