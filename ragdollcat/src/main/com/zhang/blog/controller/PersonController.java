package main.com.zhang.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import main.com.zhang.blog.entity.Person;
import main.com.zhang.blog.service.PersonService;

/** * @author zhang_chl 
    * @date 2017年7月14日下午4:49:25
    */
@Controller
@RequestMapping(value="/person")
public class PersonController {
	@Autowired
	private PersonService<Person> personService;
	
	@RequestMapping(value="/allpersons")
	public List<Person> getPersons(String hql){
		return personService.list(hql);
	}
	
	@RequestMapping(value="/partpersons")
	public List<Person> getPersons(String hql, int firstResult, int maxSize,Object...params){
		return personService.list(hql, firstResult, maxSize, params);
	}
	
	@RequestMapping(value="/findPersonById")
	public Person findPersonById(int id){
		return personService.find(Person.class, id);
	}
	
	@RequestMapping(value="/findPersonByName")
	public Person findPersonByName(String name){
		return personService.findPersonByName(name);
	}
	
	@RequestMapping(value="/findPersonByNameAndPassword")
	public Person findPersonByName(String name, String password){
		return personService.findPersonByName(name, password);
	}
	
	@RequestMapping(value="/deletedPerson")
	public void deletedPerson(Person person){
		personService.delete(person);
	}
	
	@RequestMapping(value="/savePerson")
	public void savePerson(Person person){
		personService.save(person);
	}
	
	@RequestMapping(value="/createPerson")
	public void createPerson(Person person, Model model){
		try {
			personService.create(person);
		} catch (RuntimeException e) {
			model.addAttribute("exit_error", e.getLocalizedMessage());
		}
	}
	
	@RequestMapping(value="/getTotalCount")
	public int getTotalCount(String hql, Object...params){
		return personService.getTotalCount(hql, params);
	}
	
	@RequestMapping(value="/test")
	public String test(){
		System.out.println("controller...");
		return "index";
	}
}
