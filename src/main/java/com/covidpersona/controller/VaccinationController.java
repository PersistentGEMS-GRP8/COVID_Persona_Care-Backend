package com.covidpersona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.VaccinationDetails.domain.Patient;
import com.example.VaccinationDetails.service.VaccinationService;

@Controller

public class VaccinationController { 
	 @Autowired
	    private VaccinationService service;

	 @RequestMapping(value = "/ ", method = RequestMethod.GET)
	    public String viewHomePage(Model model) {
	        List<Patient> listpatient = service.listAll();
	        model.addAttribute("listpatient", listpatient);
	        System.out.print("Get / ");	
	        return "index";
	    }

	    @GetMapping("/New")
	    public String add(Model model) {
	        model.addAttribute("patient", new Patient());
	        return "New";
	    }

	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String savePatient(@ModelAttribute("patient") Patient std) {
	        service.save(std);
	        return "redirect:/";
	    }

	    @RequestMapping("/edit/{id}")
	    public ModelAndView showEditPatientPage(@PathVariable(name = "id") int id) {
	        ModelAndView mav = new ModelAndView("New");
	        Patient std = service.get(id);
	        mav.addObject("patient", std);
	        return mav;
	        
	   	    }
	   	    @RequestMapping("/delete/{id}")
	   	    public String deletestudent(@PathVariable(name = "id") int id) {
	   	        service.delete(id);
	   	        return "redirect:/";
	   	    }

}
