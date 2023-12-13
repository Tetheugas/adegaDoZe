package com.example.adegaDoZe.controllers;

import com.example.adegaDoZe.models.Stock;
import com.example.adegaDoZe.repository.StockRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StockController {
    @Autowired
    private StockRepository sr;

    @RequestMapping(value = "/registerProduct", method = RequestMethod.GET)
    public String form(){
        return "stock/formStock";
    }

    //
    @RequestMapping(value = "/registerProduct", method = RequestMethod.POST)
    public String form(@Valid Stock stock, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Check the fields");
            return "redirect:/registerProduct";
        }

        sr.save(stock);
        attributes.addFlashAttribute("mensagem", "Product registered successfully");
        return "redirect:/registerProduct";
    }

    @RequestMapping("/products")
    public ModelAndView listProducts() {
        ModelAndView mv = new ModelAndView("stock/listProduct");
        Iterable<Stock> products = sr.findAll();
        mv.addObject("products", products);
        return mv;
    }
//    @RequestMapping(value = "/dependentes/{id}", method = RequestMethod.GET)
//    public ModelAndView teste(@PathVariable("id") long id) {
//        Stock stock = sr.findById(id);
//        ModelAndView mv = new ModelAndView("stock/listProduct");
//        mv.addObject("teste", stock);
//
//        // lista de dependentes baseada no funcionario
//        return mv;
//    }

    @RequestMapping("/deleteProduct")
    public String deleteProduct(long id) {
        Stock stock = sr.findById(id);
        sr.delete(stock);
        return "redirect:/products";
    }
}
