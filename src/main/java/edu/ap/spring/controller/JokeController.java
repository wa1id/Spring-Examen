package edu.ap.spring.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.ap.spring.jpa.Joke;
import edu.ap.spring.jpa.JokeRepository;

@Controller
@Scope("session")
public class JokeController {
	
	   @Autowired
	   JokeRepository repository;
   
   public JokeController() {
   }
       
   @RequestMapping("/joke")
   public String joke() {
	   return "joke";
   }
   
   @RequestMapping("/list")
   public String list(Model model) {
	   model.addAttribute("jokes", repository.findAll());

	   return "list";
   }
		   
   @RequestMapping(value = "/joke_post", method = RequestMethod.POST)
   public String joke_post(@RequestParam("firstName") String firstName, 
				 			@RequestParam("lastName") String lastName,
				 			Model model) {
	   
	      model.addAttribute("firstName", firstName);
	      model.addAttribute("lastName", lastName);
	      
	   URL obj;
		try {
			obj = new URL("http://api.icndb.com/jokes/random?firstName=" + firstName +"&lastName=" + lastName);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	        con.setRequestMethod("GET");
	
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();

	        model.addAttribute("res", response.toString());
	        repository.save(new Joke(firstName, lastName, response.toString()));
	        return "joke_post";
		} 
		catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
   }
   
   
   @RequestMapping("/")
   public String root() {
	   return "redirect:/joke";
   }
}
