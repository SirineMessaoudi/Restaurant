package com.ds_JEE_M_H.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ds_JEE_M_H.Services.MetService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {


	public IndexController() {
		// TODO Auto-generated constructor stub
	}
	
	private MetService metService;
	@GetMapping({"","/","index"})
	public String getIndex(Model model) {
		model.addAttribute("mets", metService.getAllMets());
		return "index";
	}

}
