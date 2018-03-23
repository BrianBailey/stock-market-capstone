package com.example.stockmarketcapstone.controllers;

import com.example.stockmarketcapstone.models.StockData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;


@Controller
@RequestMapping(value = "list")
public class ListController {

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController () {
        columnChoices.put("date", "Date");
        columnChoices.put("open", "Open");
        columnChoices.put("high", "High");
        columnChoices.put("low", "Low");
        columnChoices.put("close", "Close");
        columnChoices.put("adjClose","AdjClose");
        columnChoices.put("volume", "Volume");
        columnChoices.put("label", "Label");

        //columnChoices.put("name", "Name");



        columnChoices.put("all", "All");
    }

    @RequestMapping(value = "")
    public String list(Model model) {

        model.addAttribute("columns", columnChoices);

        return "list";
    }

    @RequestMapping(value = "values")
    public String listColumnValues(Model model, @RequestParam String column) {

        if (column.equals("all")) {
            ArrayList<HashMap<String, String>> stocks = StockData.findAll();
            model.addAttribute("title", "All Stock Information");
            model.addAttribute("stocks", stocks);
            return "list-stocks";
        } else {
            ArrayList<String> items = StockData.findAll(column);
            model.addAttribute("title", "All " + columnChoices.get(column) + " Stock Hyperlinks");
            model.addAttribute("column", column);
            model.addAttribute("items", items);
            return "list-column";
        }

    }

    @RequestMapping(value = "stocks")
    public String listJobsByColumnAndValue(Model model,
                                           @RequestParam String column, @RequestParam String value) {

        ArrayList<HashMap<String, String>> stocks = StockData.findByColumnAndValue(column, value);
        model.addAttribute("title", "Stocks with " + columnChoices.get(column) + ": " + value);
        model.addAttribute("stocks", stocks);

        return "list-stocks";
    }
}