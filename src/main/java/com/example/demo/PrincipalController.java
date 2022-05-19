package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrincipalController {

	@GetMapping("/")
	public String index() {
		//chame o template index.html da pasta templates
		return "index";
	}
	
	@GetMapping("/p1")
	public String Pagina1() {
		return "pagina1";
	}
	
	@GetMapping("/p2")
	public String Pagina2() {
		return "pagina2";

	}@GetMapping("/p3")
	public String Pagina3() {
		return "pagina3";
	}

}
