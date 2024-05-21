package com.pad.springboot.groceryinventory.rest;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pad.springboot.groceryinventory.groceryinventorydb;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LandingPageRestController {
    
	public String item ="";
	public int quantity = 0;
	public String username ="";
	
	
	// we are returning landing-page.html when the root is accessed
	@GetMapping("/")
	public String sayhello() {
	    return "landing-page";
	}
    
	
	// wec are fetching the anchor text
	@GetMapping("/getanchortext")
	public String getAnchorText(@RequestParam("at") String anchorText) {
		StringBuilder sb = new StringBuilder(anchorText.trim());
		/*
		 * sb.deleteCharAt(0); sb.deleteCharAt(sb.length() - 1);
		 */
		item = sb.toString();
		System.out.println(item);
		System.out.println(anchorText);
		boolean ifexists = groceryinventorydb.findItem(item);
		if(ifexists==false)
			groceryinventorydb.insertItem(item);	

		return "redirect:/updateqty.html";
		
	}
	
	
	// we are fetching the update page deatils
	@RequestMapping("/fetchuserqty")
	public String getuserdata(HttpServletRequest request,Model model) {
		// fetching name and qty from Updatepage form
		String usersname = request.getParameter("username");
		int qty = Integer.parseInt(request.getParameter("qty"));
		quantity = qty;
		StringBuilder sb = new StringBuilder(usersname.trim());
		username = sb.toString();
		
		Boolean update = groceryinventorydb.updateinventory(item,quantity,username);
		System.out.println("Update Successfull "+update);
		
		//model.addAttribute("message",qty);
		model.addAttribute("messageitem", item);
        model.addAttribute("messageqty", quantity);
        model.addAttribute("messageuser", username);

		return "validation";
	}
	
	@RequestMapping("/getstockReport")
	public String getstockReport(Model model) {
		
		System.out.println("inside getstockReport");
		
		LinkedHashMap<String,LinkedHashMap<Integer,String>> map = new LinkedHashMap<String, LinkedHashMap<Integer, String>>();
	    map = groceryinventorydb.getStockReport();	
	    model.addAttribute("hashmap", map);
	    System.out.println("inside getstockReport end");
	
		return "stockinventory";
		
	}


	
	

}
