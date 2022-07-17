package com.springbootproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.springbootproject.models.LocationStats;
import com.springbootproject.services.CoronavirusDataService;

@Controller
public class HomeController {
	
	@Autowired
	CoronavirusDataService coronavirusDataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronavirusDataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
		
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalCases);
		model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
	}
}
