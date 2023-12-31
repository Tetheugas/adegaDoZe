package com.example.adegaDoZe.controllers;

import com.example.adegaDoZe.models.Stock;
import com.example.adegaDoZe.repository.StockRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class StockController {
    @Autowired
    private StockRepository sr;

    @RequestMapping(value = "/registerProduct", method = RequestMethod.GET)
    public String form() {
        return "stock/formStock";
    }

    //
    @RequestMapping(value = "/registerProduct", method = RequestMethod.POST)
    public String form(@Valid Stock stock, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Check the fields");
            return "redirect:/registerProduct";
        }

        sr.save(stock);
        attributes.addFlashAttribute("message", "Product registered successfully");
        return "redirect:/registerProduct";
    }

    @RequestMapping("/products")
    public ModelAndView listProducts() {
        ModelAndView mv = new ModelAndView("stock/listProduct");
        Iterable<Stock> products = sr.findAll();
        mv.addObject("products", products);
        return mv;
    }

    @RequestMapping(value = "/update-product/{id}", method = RequestMethod.GET)
    public ModelAndView updateProducts(@PathVariable("id") long id) {
        Stock stock = sr.findById(id);
        ModelAndView mv = new ModelAndView("stock/update-product");
        mv.addObject("stock", stock);
        return mv;
    }

    @RequestMapping(value = "/update-product/{id}", method = RequestMethod.POST)
    public String updateProduct(@Valid Stock stock, BindingResult result, RedirectAttributes attributes) {

        sr.save(stock);
        attributes.addFlashAttribute("success", "Funcionário alterado com sucesso!");


        return "redirect:/products";

    }

    @RequestMapping("/deleteProduct")
    public String deleteProduct(long id) {
        Stock stock = sr.findById(id);
        sr.delete(stock);
        return "redirect:/products";
    }

}
