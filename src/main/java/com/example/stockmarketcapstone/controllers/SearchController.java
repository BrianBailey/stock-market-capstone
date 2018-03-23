package com.example.stockmarketcapstone.controllers;

import com.example.stockmarketcapstone.models.StockData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;





@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }


    @RequestMapping(value="results")
    public String search(Model model,
                         @RequestParam String searchType,
                         @RequestParam String searchTerm){
        ArrayList<HashMap<String, String>> stocks;
        if(searchType.equals("all")){
            stocks = StockData.findByValue(searchTerm);
        }
        else {
            stocks = StockData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("stocks", stocks);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";

    }


}
