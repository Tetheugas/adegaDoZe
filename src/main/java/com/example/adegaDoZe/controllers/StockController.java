package com.example.adegaDoZe.controllers;

import com.example.adegaDoZe.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StockController {
    @Autowired
    private StockRepository sr;

    @RequestMapping(value = "/registerProduct", method = RequestMethod.GET)
    public String form(){
        return "stock/formStock";
    }
}
