package com.akash.coviddata.controller;

import com.akash.coviddata.services.CoronaVirusData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusData coronavirusdata;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("locationStats", coronavirusdata.getAlldata());
        model.addAttribute("totalRecoveredInWorld", coronavirusdata.getTotalRecoveredInWorld());
        model.addAttribute("totalCasesInWorld", coronavirusdata.getTotalCasesInWorld());
        model.addAttribute("totalDeaths", coronavirusdata.getTotalDeaths());
        model.addAttribute("totalActiveCases", coronavirusdata.getTotalActiveCases());
        return "home";
    }
}
