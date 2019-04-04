package ch.hearc.spring.diconimaux.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.hearc.spring.diconimaux.jparepository.AlimentationRepository;
import ch.hearc.spring.diconimaux.jparepository.AnimalRepository;
import ch.hearc.spring.diconimaux.jparepository.ClassificationRepository;
import ch.hearc.spring.diconimaux.jparepository.LocationRepository;
import ch.hearc.spring.diconimaux.model.Animal;


@Controller
public class HomeController {


		@Autowired
		AnimalRepository animalRepository;
		@Autowired
		AlimentationRepository alimentationRepository;
		@Autowired
		ClassificationRepository classificationRepository;
		@Autowired
		LocationRepository locationRepository;
	
		
		@GetMapping("/")
		public String accueil(Map<String, Object> model) {
			model.put("page", "Accueil");
			model.put("animals", animalRepository.findAll());
			model.put("alimentations", alimentationRepository.findAll());
			model.put("locations", locationRepository.findAll());
			model.put("classifications", classificationRepository.findAll());
			
			return "home";
		}
		
		@GetMapping("/admin")
		public String admin(Map<String, Object> model) {
			model.put("page", "Admin");
			return "admin";
		}
		
		@GetMapping("/user")
		public String user(Map<String, Object> model) {
			model.put("page", "User");
			return "user";
		}
		
		@Autowired
		@GetMapping("/animals")
		public String getAllAnimal(Map<String, Object> model)
		{
			model.put("animals", animalRepository.findAll());
			model.put("alimentations", alimentationRepository.findAll());
			model.put("locations", locationRepository.findAll());
			model.put("classifications", classificationRepository.findAll());
			
			return "listeAnimal";
		}	
		
		@PostMapping("/animal")
		public String saveAnimal(@Valid @ModelAttribute Animal animal, BindingResult errors, Model model) {
					
			if (!errors.hasErrors()) {
	            animalRepository.save(animal);
	            
	            
	        }
	        return ((errors.hasErrors()) ? "animal" : "redirect:basic");
		}
}

