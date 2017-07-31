package test.test_service;

import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.com.zhang.blog.entity.Person;
import main.com.zhang.blog.service.PersonService;

/** * @author zhang_chl 
    * @date 2017年7月15日下午3:51:01
    */
@Component
public class TestPersonService {
	@Autowired
	private PersonService<Person> personService;
	
	@Test
	public void test(){
		Person p = new Person();
		p.setName("张正正");
		p.setPassward("zzz");
		p.setEmail("zhang_chll@163.com");
		p.setDeleted(false);
		p.setDatecreated(Calendar.YEAR+"-"+Calendar.MONTH+"-"+Calendar.DATE);
		p.setEnableComment(false);
		p.setStatus(0);
		personService.create(p);
	}
	
}
